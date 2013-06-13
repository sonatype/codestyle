/** @constructor */
ClassNamesLikeThis = function() {
  /** @private */
  this.privateProperty_ = 2;

  /** @protected */
  this.protectedProperty = 4;
};

/** @private */
ClassNamesLikeThis.staticPrivateProp_ = 1;

/** @protected */
ClassNamesLikeThis.staticProtectedProp = 31;

ClassNamesLikeThis.CONSTANT_NAME = 10;

ClassNamesLikeThis.EnumName = {
  VALUE_ONE: 1,
  VALUE_TWO: 2
};

/** @private */
ClassNamesLikeThis.prototype.privateMethod_ = function() {
};

/** @protected */
ClassNamesLikeThis.prototype.protectedMethod = function() {
};