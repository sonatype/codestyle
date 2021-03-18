module.exports = {
  // largely we use google's style guide with some exceptions
  'extends': ['google'],
  'rules': {
    /**
     * Our code-style *requires* that all braces be followed by newlines.
     * Example:
     *
     * invalid:
     * if(...) {
     *   ...
     * } else {
     *   ...
     * }
     *
     * valid:
     * if(...) {
     *   ...
     * }
     * else {
     *   ...
     * }
     */
    'brace-style':
        [
          'error',
          'stroustrup',
        ],
  },
};
