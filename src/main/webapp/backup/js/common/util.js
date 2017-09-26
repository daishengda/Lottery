function $id(id){
	return document.getElementById(id);
}

function $name(name){
	return document.getElementsByName(name)[0];
}

function $names(name){
	return document.getElementsByName;
}

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/, "");
}
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/, "");
}

function MapEntry(vKey, vValue) {
	this.key = vKey
	this.value = vValue;
	this.nextEntry = null;
	this.toString = function() {
		return this.key + ":" + this.value;
	}
}

function Map() {
	this.entryCount = 0;
	this.headEntry = null;
	this.put = function(vKey, vValue) {
		var entry = this.getEntry(vKey);
		if (entry != null) {
			entry.value = vValue;
			return;
		}
		var entry = new MapEntry(vKey, vValue);
		if (this.headEntry == null)
			this.headEntry = entry;
		else
			this.getTail().nextEntry = entry;
		this.entryCount++;
	}
	this.size = function() {
		return this.entryCount;
	}
	this.getEntry = function(vKey) {
		if (this.headEntry == null)
			return null;
		else {
			var currentEntry = this.headEntry;
			while (currentEntry.key != vKey) {
				if (currentEntry.nextEntry == null)
					return null;
				currentEntry = currentEntry.nextEntry;
			}
			return currentEntry;
		}
	}
	this.get = function(vKey) {
		var entry = this.getEntry(vKey);
		if (entry == null)
			return null;
		return entry.value;
	}
	this.getTail = function() {
		if (this.headEntry == null)
			return null;
		else {
			var currentEntry = this.headEntry;
			while (currentEntry.nextEntry != null)
				currentEntry = currentEntry.nextEntry;
			return currentEntry;
		}
	}
	this.keySet = function() {
		var result = new Array;
		var currentEntry = this.headEntry;
		while (currentEntry != null) {
			result[result.length] = currentEntry.key;
			currentEntry = currentEntry.nextEntry;
		}
		return result;
	}
	this.toString = function() {
		var result = "";
		var currentEntry = this.headEntry;
		while (currentEntry != null) {
			if (result != "")
				result += ",";
			result += (currentEntry.key + ":" + currentEntry.value);
			currentEntry = currentEntry.nextEntry;
		}
		return result;
	}
	this.remove = function(vKey) {
		if (this.headEntry == null)
			return;

		if (this.headEntry.key == vKey) {
			this.headEntry = this.headEntry.nextEntry;
			return;
		}
		var currentEntry = this.headEntry;
		while (currentEntry.nextEntry != null) {

			if (currentEntry.nextEntry.key == vKey) {
				currentEntry.nextEntry = currentEntry.nextEntry.nextEntry;
				return;
			}
			currentEntry = currentEntry.nextEntry;
		}

	}
}

function List() {
	var values = new Array;
	this.add = function(value) {
		values.length++;
		values[values.length - 1] = value;
	}
	this.size = function() {
		return values.length;
	}
	this.get = function(index) {
		return values[index];
	}
	this.remove = function(value) {
		var index = null;
		for (var i = 0; i < values.length; i++)
			if (values[i] == value)
				index = i;
		if (index == null)
			return;
		for (var i = index; i < values.length - 1; i++)
			values[i] = values[i + 1];
		values.length--;
	}
	this.toString = function() {
		return values.toString();
	}
	this.contains = function(value) {
		for (var i = 0; i < values.length; i++)
			if (values[i] == value)
				return true;
		return false;
	}
}

//格式数字金额[-123456.7] to [-123,456.70]
var fomatMoney = function(money) {
	var isMinus = false;
	if (money.indexOf('-') >= 0) {
		money = money.substr(1, money.length)
		isMinus = true;
	}
	if (/[^0-9\.]/.test(money)) {
		return money;
	}
	money = money.replace(/^(\d*)$/, "$1.");
	money = (money + "00").replace(/(\d*\.\d\d)\d*/, "$1");
	money = money.replace(".", ",");
	var re = /(\d)(\d{3},)/;
	while (re.test(money)) {
		money = money.replace(re, "$1,$2");
	}
	money = money.replace(/,(\d\d)$/, ".$1");

	money = money.replace(/^\./, "0.");

	if (isMinus) {
		money = '-' + money;
	}
	return money;
}

//还原数字金额[-123,456.78]
function reFomatMoney(money) {
	money = money.replace(new RegExp(',', "gm"), '');
	return money;
}

function getBytesLen(B) {
	if (B) {
		var A = /[^\x00-\xff]/g;
		return (B + "").replace(A, "aa").length
	} else {
		if (B == "")
			return 0;
		return -1
	}
}

function StringBuffer() {
	this.values = new Array()
}
StringBuffer.prototype.append = function(A) {
	this.values.push(A);
	return this
};
StringBuffer.prototype.clear = function() {
	return this.values = []
};
StringBuffer.prototype.toString = function() {
	return this.values.join("")
};