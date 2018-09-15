'use strict';
let gulp = require('gulp');
let gulpif = require('gulp-if'); // gul条件判断
let rename = require('gulp-rename'); // 重命名
let uglify = require('gulp-uglify-es').default; // js压缩
let runSequence = require('run-sequence'); // 任务执行排序
let htmlmin = require('gulp-htmlmin'); // 压缩html
let revCollector = require('./gulp-app.js'); // 修改版本号
let smushit = require('gulp-smushit'); // 压缩图片
let cleanCSS = require('gulp-clean-css'); // 压缩css文件
let combiner = require('stream-combiner2');
let babel = require('gulp-babel');
let browserify = require('browserify');
let buffer = require('vinyl-buffer');
let babelify = require('babelify');
let tap = require('gulp-tap');
let jlibInclude = require('./jlib-include.json');

let DEST = './webapp/';
let SRC = './websrc/';
let config = {
    img: {
        SRC: SRC + '/**/*.+(png|jpg|gif)', // 要压缩图片的源目录及类型
        // DEST: DEST //压缩图片输出的目的目录
        minFlag: false,
    },
    css: {
        SRC: SRC + '/**/*.css', // css文件改版本号的源目录
        // DEST: DEST, //css文件输出的目的目录
        // DEST_MIN: DEST //压缩css文件输出的目的目录
    },
    js: {
        SRC: SRC + '/**/*.js', // js文件改版本号的源目录
        // DEST: DEST, //js文件输出的目的目录
        // DEST_MIN: DEST //压缩js的min文件输出目录
        compileFlag: false,
    },
    html: {
        SRC: SRC + '/**/*.+(html|jsp|htm)', // html文件改版本号的源目录
        // DEST: DEST, //html文件输出的目的目录
        minFlag: false, // html文件是否压缩
    },
    others: {
        SRC: SRC + '/**/*.!(png|jpg|gif|css|js|html|jsp|htm)',
    },
    libs: {
        DEST: DEST + 'static/jlib/',
    },
    REVConifg: { // 文件要更换版本号需要的配置项
        vStr: '5.2.', // 版本号前缀
        pathRep: {
            '${RESOURCE_STATIC_URL}': './websrc/static',
            '${RESOURCE_LANGUAGE}': 'zh_CN',
            '${RESOURCE_JLIB_URL}': './node_modules',
        },
    },
    TASK_seq: [ // 指定执行任务及其顺序
        'revImg',
        'revCSS',
        'revJS',
        'revHtml',
        'copyLibs',
        'copyOthers',
    ],
};

let isNotMinified = function(f) {
    if (f.path.endsWith('.min.js') || f.path.endsWith('.min.css')) {
        return false;
    }
    return true;
};

gulp.task('revImg', function() {
    let combined = combiner.obj([
        gulp.src(config.img.SRC),
        gulpif(config.img.minFlag, smushit({
            verbose: true,
        })),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('revCSS', function() {
    let combined = combiner.obj([
        gulp.src(config.css.SRC),
        revCollector(config.REVConifg),
        gulpif(isNotMinified, cleanCSS({
            rebase: false,
            inline: false,
        })),
        gulpif(isNotMinified, rename({
            suffix: '.min',
        })),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('revJS', function() {
    let combined = combiner.obj([
        gulp.src(config.js.SRC),
        revCollector(config.REVConifg),
        gulpif(config.js.compileFlag, babel({
            presets: [
                ['@babel/env', {
                    'targets': 'ie >= 9, Firefox ESR, last 2 versions',
                }],
            ],
        })),
        uglify(),
        gulpif(isNotMinified, rename({
            suffix: '.min',
        })),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('revHtml', function() {
    let options = {
        removeComments: true,
        collapseWhitespace: true,
        collapseBooleanAttributes: true,
        removeEmptyAttributes: false,
        removeScriptTypeAttributes: true,
        removeStyleLinkTypeAttributes: true,
        minifyJS: true,
        minifyCSS: true,
    };
    let combined = combiner.obj([
        gulp.src(config.html.SRC),
        revCollector(config.REVConifg),
        gulpif(config.html.minFlag, htmlmin(options)),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('copyOthers', () => {
    let combined = combiner.obj([
        gulp.src(config.others.SRC),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('copyLibs', () => {
    let dependencies = jlibInclude;
    let src = [];
    for (let key in dependencies) {
        if (dependencies.hasOwnProperty(key)) {
            src.push('./node_modules/' + key + '/' + jlibInclude[key] +
                '/**/*');
        }
    }
    let combined = combiner.obj([
        gulp.src(src, {
            base: './node_modules/',
        }),
        gulp.dest(config.libs.DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('testBabel', () => {
    let combined = combiner.obj([
        gulp.src(config.js.SRC),
        babel({
            presets: [
                ['@babel/env', {
                    'targets': 'ie >= 9, Firefox ESR, last 2 versions',
                }],
            ],
            plugins: ['@babel/plugin-transform-runtime'],
        }),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('browserify', () => {
    let combined = combiner.obj([
        gulp.src(config.js.SRC, {
            read: false,
        }),
        tap((file) => {
            file.contents = browserify({
                entries: file.path,
                transform: [babelify.configure({
                    presets: [
                        ['@babel/env', {
                            'targets': {
                                'ie': '9',
                            },
                        }],
                    ],
                    plugins: ['@babel/plugin-transform-runtime'],
                })],
            }).bundle();
        }),
        buffer(),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('dev', function(done) {
    runSequence(
        config.TASK_seq,
        done);
});

gulp.task('default', ['dev']);
