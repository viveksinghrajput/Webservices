
(function (global, $) {

var _hash = "!";
var noBackPlease = function () {
global.location.href += "#";
global.setTimeout(function () {
global.location.href += "!";
}, 5);
};

global.onhashchange = function () {
if (global.location.hash != _hash) {
global.location.hash = _hash;
}
};

global.onload = function () {
noBackPlease();
};

})(window, jQuery || window.jQuery);