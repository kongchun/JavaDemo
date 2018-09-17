let thrift = require('thrift');
let DemoService = require('./DemoService');
let demoTypes = require('./demo_types');

let server = thrift.createServer(DemoService, {
    throwException: (flag) => {
        if (flag) {
            throw new demoTypes.CustomException({id:1, name:'manual', description:'desc'});
        }
        return 'Throw Custom Exception Function.';
    },
    helloWorld: () => {
        return 'hello, '+demoTypes.hello.hello;
    }
});

server.listen(9090);