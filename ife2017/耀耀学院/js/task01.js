/**
 * Created by 见此良人 on 2017/6/26.
 */
var input = document.getElementById("inputText1");
var btn=document.getElementById("btn");
var hint=document.getElementById("hint");

function check() {
    var str = input.value;
    var len = 0;
    var patt1 = /[^\x00-\xff]/;
    if (str == null || str == "") {
        hint.innerHTML="名称不能为空";
        input.id = "inputWarn";
    }else {
        for (var i = 0; i < str.length; i++) {
            if (patt1.test(str)) {
                len += 2;
                // i++;
            } else {
                len += 1;
            }
        }
        if (len<4||len>16){
            hint.innerHTML="名称格式错误";
            input.id="inputWarn";
        }else {
            hint.innerHTML="名称格式正确";
            input.id="inputPass";
        }
    }
}
btn.addEventListener("click",check);