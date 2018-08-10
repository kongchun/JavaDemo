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
	}).fail(()=>{
		result.html('失败');
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
		result.html(JSON.stringify(data));
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
	}).fail(()=>{
		result.html('失败');
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
function hmset() {
	result.html('');
	$.get("hmset?key=" + $('#hmset').val().trim() + "&map="
			+ $('#hmset-map').val().trim(), (data)=>{
		let text;
		if (data) {
			text = '成功';
		} else {
			text = '失败';
		}
		result.html(text);
	}).fail(()=>{
		result.html('失败');
	});
}
function hgetall() {
	result.html('');
	$.get("hgetall?key=" + $('#hgetall').val().trim(), (data)=>{
		result.html(JSON.stringify(data));
	});
}
function hset() {
	result.html('');
	$.get("hset?key=" + $('#hset').val().trim() + "&hashKey="
			+ $('#hset-hashkey').val().trim() + "&value="
			+ $('#hset-value').val().trim(), (data)=>{
		let text;
		if (data) {
			text = '成功';
		} else {
			text = '失败';
		}
		result.html(text);
	}).fail(()=>{
		result.html('失败');
	});
}
function hdel() {
	result.html('');
	$.get("hdel?key=" + $('#hdel').val().trim() + "&list="
			+ $('#hdel-list').val().trim(), (data)=>{
		result.html('已删除'+data+'个');
	});
}
function sadd() {
	result.html('');
	$.get("sadd?key=" + $('#sadd').val().trim() + "&list="
			+ $('#sadd-list').val().trim(), (data)=>{
		result.html('已存入'+data+'个');
	});
}
function smembers() {
	result.html('');
	$.get("smembers?key=" + $('#smembers').val().trim(), (data)=>{
		result.html(JSON.stringify(data));
	});
}
function sinter() {
	result.html('');
	$.get("sinter?list=" + $('#sinter').val().trim(), (data)=>{
		result.html(JSON.stringify(data));
	});
}
function sunion() {
	result.html('');
	$.get("sunion?list=" + $('#sunion').val().trim(), (data)=>{
		result.html(JSON.stringify(data));
	});
}
function srem() {
	result.html('');
	$.get("srem?key=" + $('#srem').val().trim() + "&list="
			+ $('#srem-list').val().trim(), (data)=>{
		result.html('已删除'+data+'个');
	});
}
function zadd() {
	result.html('');
	$.get("zadd?key=" + $('#zadd').val().trim() + "&set="
			+ $('#zadd-set').val().trim(),(data)=>{
		let text;
		if (data) {
			text = '成功';
		} else {
			text = '失败';
		}
		result.html(text);
	});
}
function zrange() {
	result.html('');
	$.get("zrange?key=" + $('#zrange').val().trim() + "&start=" + $('#zrange-start').val().trim()
			+ "&end="+ $('#zrange-end').val().trim(), (data)=>{
		result.html(JSON.stringify(data));
	});
}
function pipeline(){
	result.html('');
	$.get("pipeline?ekey=" + $('#exists').val().trim() + "&dkey=" + $('#del').val().trim() + "&expkey="
			+ $('#expire').val().trim() + "&timeout="+ $('#expire-timeout').val() + "&tkey=" + $('#ttl').val().trim(),(data)=>{
		result.html(JSON.stringify(data));
	}).fail(()=>{
		result.html('失败');
	});
}