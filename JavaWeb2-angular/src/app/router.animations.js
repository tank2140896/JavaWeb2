"use strict";
exports.__esModule = true;
var animations_1 = require("@angular/animations");
function routerTransition() {
    return slideToBottom();
}
exports.routerTransition = routerTransition;
function slideToRight() {
    return animations_1.trigger('routerTransition', [
        animations_1.state('void', animations_1.style({})),
        animations_1.state('*', animations_1.style({})),
        animations_1.transition(':enter', [
            animations_1.style({ transform: 'translateX(-100%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateX(0%)' }))
        ]),
        animations_1.transition(':leave', [
            animations_1.style({ transform: 'translateX(0%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateX(100%)' }))
        ])
    ]);
}
exports.slideToRight = slideToRight;
function slideToLeft() {
    return animations_1.trigger('routerTransition', [
        animations_1.state('void', animations_1.style({})),
        animations_1.state('*', animations_1.style({})),
        animations_1.transition(':enter', [
            animations_1.style({ transform: 'translateX(100%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateX(0%)' }))
        ]),
        animations_1.transition(':leave', [
            animations_1.style({ transform: 'translateX(0%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateX(-100%)' }))
        ])
    ]);
}
exports.slideToLeft = slideToLeft;
function slideToBottom() {
    return animations_1.trigger('routerTransition', [
        animations_1.state('void', animations_1.style({})),
        animations_1.state('*', animations_1.style({})),
        animations_1.transition(':enter', [
            animations_1.style({ transform: 'translateY(-100%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateY(0%)' }))
        ]),
        animations_1.transition(':leave', [
            animations_1.style({ transform: 'translateY(0%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateY(100%)' }))
        ])
    ]);
}
exports.slideToBottom = slideToBottom;
function slideToTop() {
    return animations_1.trigger('routerTransition', [
        animations_1.state('void', animations_1.style({})),
        animations_1.state('*', animations_1.style({})),
        animations_1.transition(':enter', [
            animations_1.style({ transform: 'translateY(100%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateY(0%)' }))
        ]),
        animations_1.transition(':leave', [
            animations_1.style({ transform: 'translateY(0%)' }),
            animations_1.animate('0.5s ease-in-out', animations_1.style({ transform: 'translateY(-100%)' }))
        ])
    ]);
}
exports.slideToTop = slideToTop;
//# sourceMappingURL=router.animations.js.map