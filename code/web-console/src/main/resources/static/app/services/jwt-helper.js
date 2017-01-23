angular.module('sr-app')

    .service('JwtHelper', function($window) {

        this.urlBase64Decode = function(str) {
            var output = str.replace(/-/g, '+').replace(/_/g, '/');
            switch (output.length % 4) {
                case 0: {
                    break;
                }
                case 2: {
                    output += '==';
                    break;
                }
                case 3: {
                    output += '=';
                    break;
                }
                default: {
                    throw 'Illegal base64url string!';
                }
            }
            return $window.decodeURIComponent(escape($window.atob(output)));
        };

        this.decodeToken = function(token) {
            var parts = token.split('.');
            if (parts.length !== 3) {
                throw new Error('JWT must have 3 parts');
            }

            var decoded = this.urlBase64Decode(parts[1]);
            if (!decoded) {
                throw new Error('Cannot decode the token');
            }

            return angular.fromJson(decoded);
        };

        this.getUser = function(token) {
            var decoded = this.decodeToken(token);
            if (typeof decoded.sub === "undefined") {
                return null;
            } else {
                return decoded.sub;
            }
        };

        this.getTokenExpirationDate = function(token) {
            var decoded = this.decodeToken(token);
            if (typeof decoded.exp === "undefined") {
                return null;
            }

            var d = new Date(0);
            d.setUTCSeconds(decoded.exp);
            return d;
        };

        this.isTokenExpired = function(token, offsetSeconds) {
            var d = this.getTokenExpirationDate(token);
            offsetSeconds = offsetSeconds || 0;
            if (d === null) {
                return false;
            }
            return !(d.valueOf() > (new Date().valueOf() + (offsetSeconds * 1000)));
        };
    });