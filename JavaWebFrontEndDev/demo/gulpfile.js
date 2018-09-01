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
let browserify = require('browserify');
let source = require('vinyl-source-stream');
let buffer = require('vinyl-buffer');
let sourcemaps = require('gulp-sourcemaps');

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
    },
    html: {
        SRC: SRC + '/**/*.+(html|jsp)', // html文件改版本号的源目录
        // DEST: DEST, //html文件输出的目的目录
        minFlag: false, // html文件是否压缩
    },
    others: {
        SRC: SRC + '/**/*.!(png|jpg|gif|css|js|html|jsp)',
    },
    REVConifg: { // 文件要更换版本号需要的配置项
        vStr: '5.2.', // 版本号前缀
        pathRep: {
            '${RESOUCE_STATIC_URL}': './websrc/static',
            '${RESOUCE_LANGUAGE}': 'zh_CN',
        },
    },
    TASK_seq: [ // 指定执行任务及其顺序
        'revImg',
        'revCSS',
        'revJS',
        'revHtml',
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
        gulpif(isNotMinified, uglify()),
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
gulp.task('copyOthers', function() {
    let combined = combiner.obj([
        gulp.src(config.others.SRC),
        gulp.dest(DEST),
    ]);
    combined.on('error', console.error.bind(console));
    return combined;
});
gulp.task('browserify', function() {
    let b = browserify({
        entries: './websrc/static/js/content.js',
        debug: true,
    });
    let combined = combiner.obj([
        b.bundle(),
        source('content.js'),
        buffer(),
        sourcemaps.init({
            loadMaps: true,
        }),
        uglify(),
        sourcemaps.write('./'),
        gulp.dest('./dist/js/'),
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
