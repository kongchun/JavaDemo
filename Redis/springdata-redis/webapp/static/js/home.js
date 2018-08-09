let result = $('#result');
function exists() {
	result.html('');
	$.get("exists?key=" + $('#exists').val().trim(), (data)=>{
		let text;
		if (data) {
			text = '存在';
		} else {
			text = '不存在';
		}
		result.html(text);
	});
}
function del() {
	result.html('');
	$.get("del?key=" + $('#del').val().trim(), (data)=>{
		let text;
		if (data) {
			text = '成功';
		} else {
			text = '失败';
		}
		result.html(text);
	});
}
function expire() {
	result.html('');
	$.get("expire?key=" + $('#expire').val().trim() + "&timeout="
			+ $('#expire-timeout').val(), (data)=>{
		let text;
		if (data) {
			text = '成功';
		} else {
			text = '失败';
		}
		result.html(text);
	});
}
function ttl() {
	result.html('');
	$.get("ttl?key=" + $('#ttl').val().trim(), (data)=>{
		result.html(data + '秒');
	});
}
function set() {
	result.html('');
	$.get("set?key=" + $('#set').val().trim() + "&value="
			+ $('#set-value').val().trim(), (data)=>{
		let text;
		if (data) {
			text = '成功';
		} else {
			text = '失败';
		}
		result.html(text);
	});
}
function get() {
	result.html('');
	$.get("get?key=" + $('#get').val().trim(), (data)=>{
		result.html(data);
	});
}
function incr() {
	result.html('');
	$.get("incr?key=" + $('#incr').val().trim() + "&delta="
			+ $('#incr-delta').val().trim(), (data)=>{
		result.html(data);
	});
}
function decr() {
	result.html('');
	$.get("decr?key=" + $('#decr').val().trim() + "&delta="
			+ $('#decr-delta').val().trim(), (data)=>{
		result.html(data);
	});
}
function rpush() {
	result.html('');
	$.get("rpush?key=" + $('#rpush').val().trim() + "&list="
			+ $('#rpush-list').val().trim(), (data)=>{
		result.html('List大小：'+data);
	});
}
function lrange() {
	result.html('');
	$.get("lrange?key=" + $('#lrange').val().trim() + "&start="
			+ $('#lrange-start').val().trim() + "&end="
			+ $('#lrange-end').val().trim(), (data)=>{
		let text = new Array();
		data.forEach((value)=>{
			text.push(value+' ');
		})
		result.html(text);
	});
}
function lset() {
	result.html('');
	$.get("lset?key=" + $('#lset').val().trim() + "&index="
			+ $('#lset-index').val().trim() + "&value="
			+ $('#lset-value').val().trim(), (data)=>{
		let text;
		if (data) {
			text = '成功';
		} else {
			text = '失败';
		}
		result.html(text);
	});
}
function lrem() {
	result.html('');
	$.get("lrem?key=" + $('#lrem').val().trim() + "&count="
			+ $('#lrem-count').val().trim() + "&value="
			+ $('#lrem-value').val().trim(), (data)=>{
		result.html('已删除'+data+'个');
	});
}