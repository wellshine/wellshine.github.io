/**
 * Created by 见此良人 on 2017/6/3.
 */
var text = document.getElementById("text");
var btnLeftIn = document.getElementById("btnLeftIn");
var btnRightIn = document.getElementById("btnRightIn");
var btnLeftOut = document.getElementById("btnLeftOut");
var btnRightOut = document.getElementById("btnRightOut");
var outNum = document.getElementById("outNum");
var reset=document.getElementById("reset");
var keywords=document.getElementById("keywords");
var search=document.getElementById("search");
var data=[];
//过滤数据
function filter() {
    var words=text.value;
    words=words.replace(/[^0-9a-zA-Z\u4e00-\u9fa5]+/g," ");
    var arr=words.split(" ");
    return arr;
}
//搜索数据
function searchword() {
    var reg=keywords.value;
    for (var i=0;i<outNum.childNodes.length;i++){
       if (outNum.childNodes[i].innerText.match(reg)) {
           outNum.childNodes[i].style.backgroundColor = "black";
       }
    }
    keywords.value="";
}
//插入数据
function insert(direction) {
       var arr=filter();
       for (var i=0;i<arr.length;i++){
           addLi = document.createElement("li");
           addLi.innerHTML = arr[i];
           if (direction === "left") {
               outNum.insertBefore(addLi, outNum.firstChild);
               text.value = "";
               text.focus();
           } else if (direction === "right") {
               outNum.appendChild(addLi);
               text.value = "";
               text.focus();
           }
       }
}
//删除
function del(direction) {
    if (outNum.childNodes.length === 0) {
        alert("没有数字可删除");
    } else {
        if (direction === "left") {
            alert("删除" + outNum.firstChild.innerText);
            outNum.removeChild(outNum.firstChild);
        } else if (direction === "right") {
            alert("删除" + outNum.lastChild.innerText);
            outNum.removeChild(outNum.lastChild);
        } else {
            alert("意外错误")
        }
    }
}
btnLeftIn.addEventListener("click", function () {
    insert("left");
});
btnRightIn.addEventListener("click", function () {
    insert("right");
});
btnLeftOut.addEventListener("click", function () {
    del("left");
});
btnRightOut.addEventListener("click", function () {
    del("right");
});
outNum.addEventListener("click", function (ev) {
    outNum.removeChild(ev.target);
});
reset.addEventListener("click",function () {
   outNum.innerHTML="";
});
search.addEventListener("click",searchword);
