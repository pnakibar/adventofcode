var crypto = require('crypto')

var mykey = "ckczppom"
var number = 0

function firstN(n) {
	return function (str){
		for(var i = 0; i < n; i++)
			if (str[i] != '0')
				return false
		return true
	}
}

var firstFive0 = firstN(5)
var firstSix0 = firstN(6)

function applyMd5(str){
	return crypto.createHash('md5').update(str).digest('hex')
}

while(!firstFive0(applyMd5(mykey+number))){
	number++
}

console.log("with 5 zeroes: "+number)

var number = 0
while(!firstSix0(applyMd5(mykey+number))){
	number++
}
console.log("with 6 zeroes: "+number)
console.log(applyMd5(mykey+number))


	



