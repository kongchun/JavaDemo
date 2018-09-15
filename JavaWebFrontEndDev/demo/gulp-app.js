/* eslint-disable max-len */
/* eslint-disable no-invalid-this */
'use strict';
let _ = require('underscore');
let through = require('through2');
let fs = require('fs');
let packageJson = require('./package.json');

let config = {
    reg: {
        'css': true,
        'js': true,
        'less': true,
        'img': true,
        'img1': true,
        'imgHtml': true,
    },
};

let reg = {
    'css': /<link.*href=("|')([^\"\']+.css((\?[a-z]|\_|\.)=([0-9a-zA-Z\.\_]+))*)("|')(.*)?\/?>/,
    'less': /<link.*href=("|')([^\"\']+.less((\?[a-z]|\_|\.)=([0-9a-zA-Z\.\_]+))*)("|')(.*)?\/?>/,
    'js': /src=("|')([^\"\']+)("|')(.*)?<\/script>/,
    'img': /url( *)?\("([^\"]+)"\)/,
    'img1': /url( *)?\(\'([^\']+)\'\)/,
    'imgHtml': /<img.*src=("|')([^\"\']+)("|')(.*)?\/?>/,
};
/**
 * 版本号控制器
 * @param {*} opts
 * @return {*}
 */
function revCollector(opts) {
    if (opts.extendReg) {
        for (let i = 0; i < opts.extendReg.length; i++) {
            config.reg[opts.extendReg[i].name] = true;
            reg[opts.extendReg[i].name] = opts.extendReg[i].regExp;
        }
    }
    opts = _.defaults((opts || {}), config);
    let mutables = [];
    return through.obj(function(file, enc, cb) {
        if (!file.isNull()) {
            mutables.push(file);
        }
        cb();
    }, function(cb) {
        let changes = [];
        for (let key in opts.reg) {
            if (opts.reg[key]) {
                changes.push({
                    name: key,
                    reg: reg[key],
                });
            }
        }
        mutables.forEach(function(file) {
            if (!file.isNull()) {
                let src = file.contents.toString('utf8');
                let srcArry = src.split('\n');
                for (let i = 0; i < srcArry.length; i++) {
                    changes.forEach(function(r) {
                        if (r.reg.test(srcArry[i])) {
                            let [modTime, suffix, isMin, libVer] = checkFile(file, srcArry[i], r, opts);
                            let vStr = '';
                            if (modTime) {
                                let regexp = '\\.' + suffix + '(?!\\/)((\\?[a-z]|\\_|\\.)=([0-9a-zA-Z._]*))*';
                                if ('' !== libVer) {
                                    vStr = libVer;
                                } else {
                                    vStr = opts.vStr;
                                }
                                if ((!isMin) && ('' === libVer) && (suffix.includes('js') || suffix.includes('css'))) {
                                    suffix = 'min.' + suffix;
                                }
                                let repReg = new RegExp(regexp, 'g');
                                srcArry[i] = srcArry[i].replace(repReg, '.' + suffix + '?v=' + vStr + modTime);
                            }
                        }
                    });
                }
                file.contents = Buffer.from(srcArry.join('\n'));
            }
            this.push(file);
        }, this);

        cb();
    });
}

/**
 * 查看引用文件是否存在及引用文件的修改日期、 后缀名
 * @param {*} file
 * @param {*} line
 * @param {*} r
 * @param {*} opts
 * @return {*}
 */
function checkFile(file, line, r, opts) {
    let path = file.path;
    let basePath = path.substr(0, path.lastIndexOf('\\'));
    let patt = new RegExp(r.reg);
    let exec = patt.exec(line);
    let relativePath = exec[2];
    let modTime = '';
    let suffix = '';
    let isMin = false;
    let libVer = '';
    if (relativePath) {
        let lastTimeV = relativePath.split('?')[1];
        relativePath = relativePath.split('?')[0];
        let lastIndexOfDot = relativePath.lastIndexOf('.');
        let preSuffix = relativePath.substr(relativePath.lastIndexOf('.', lastIndexOfDot - 1) + 1, 4);
        suffix = relativePath.substr(lastIndexOfDot + 1);
        if ('min.' === preSuffix) {
            isMin = true;
        }
        if (relativePath.includes('JLIB')) {
            let dependencies = packageJson.dependencies;
            let pathArray = relativePath.split('/');
            let packageName = pathArray[1];
            if (packageName.startsWith('@')) {
                packageName = packageName + '/' + pathArray[2];
            }
            libVer = dependencies[packageName] + '.';
        }
        let reg = /\$\{([a-zA-Z0-9\_\.])*\}/;
        if (!reg.test(relativePath)) {
            relativePath = basePath.replace(/\\/g, '/') + '/' + relativePath;
        }
        while (reg.test(relativePath)) {
            relativePath = relativePath.replace(reg, opts.pathRep[new RegExp(reg).exec(relativePath)[0]]);
        }
        try {
            let statInfo = fs.statSync(relativePath);
            modTime = new Date(statInfo.mtime).getTime();
        } catch (e) {
            modTime = false;
            console.log('文件不存在：' + relativePath + ' 原始：' + exec[2]);
        }
        if (lastTimeV && (lastTimeV.substr(lastTimeV.lastIndexOf('.') + 1) == modTime)) {
            modTime = false;
        }
    } else {
        console.log(exec);
    }
    return [modTime, suffix, isMin, libVer];
}
module.exports = revCollector;
