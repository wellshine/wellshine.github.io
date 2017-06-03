/**
 * Created by 见此良人 on 2017/6/3.
 */
var inNum = document.getElementById("inNum");
var btnLeftIn = document.getElementById("btnLeftIn");
var btnRightIn = document.getElementById("btnRightIn");
var btnLeftOut = document.getElementById("btnLeftOut");
var btnRightOut = document.getElementById("btnRightOut");
var outNum = document.getElementById("outNum");
var quickSort = document.getElementById("sort");

//插入数据
function insert(direction) {
    if (inNum.value === "") {
        alert("您还没有输入数字");
    } else if (isNaN(inNum.value)) {
        alert("输入的不是数字，请重新输入");
    } else if (inNum.value < 10 || inNum.value > 100) {
        alert("请输入10到100间的数字");
        inNum.value = "";
    } else if (outNum.childNodes.length >= 60) {
        alert("超过限制")//限制元素数量最多60个
    }
    else {
        addLi = document.createElement("li");
        addLi.innerHTML = inNum.value;
        addLi.style.height = 2 * inNum.value + "px";
        if (direction === "left") {
            outNum.insertBefore(addLi, outNum.firstChild);
            inNum.value = "";
            inNum.focus();
        } else if (direction === "right") {
            outNum.appendChild(addLi);
            inNum.value = "";
            inNum.focus();
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
quickSort.addEventListener("click", function () {
    var arr = [];
    for (var i = 0; i < outNum.childNodes.length; i++) {
        arr.push(outNum.childNodes[i].innerText);
    }

    arr.sort(function (a, b) {
        return a - b;
    });
    for (var c = 0; c < outNum.childNodes.length; c++) {
        outNum.childNodes[c].innerHTML = arr[c];
        outNum.childNodes[c].style.height = 3 * arr[c] + "px";
    }


})