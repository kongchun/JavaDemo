function A(callback) {
    setTimeout(function() {
        console.log("A");
        callback();
    }, 1000);
}

function B(callback) {
    setTimeout(function() {
        console.log("B");
        callback();
    }, 500);
}

function asyncFun(arr) {
    (async () => {
        for (let i in arr) {
            await new Promise((resolve) => {
                arr[i](resolve);
            });
        }
    })();
}

asyncFun([A, B, B, A]);