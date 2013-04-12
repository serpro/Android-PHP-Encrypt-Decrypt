<?php 

class MCrypt
{
        private $iv = 'fedcba9876543210'; #Same as in JAVA
        private $key = '0123456789abcdef'; #Same as in JAVA


        function __construct()
        {
        }

        function encrypt($str) {
          $iv = $this->iv;

          $td = mcrypt_module_open('rijndael-128', ' ', 'cbc', $iv);

          mcrypt_generic_init($td, $this->key, $iv);
          $encrypted = mcrypt_generic($td, utf8_decode($str));

          mcrypt_generic_deinit($td);
          mcrypt_module_close($td);

          return bin2hex($encrypted);
        }

        function decrypt($code) {
          $code = $this->hex2bin($code);
          $iv = $this->iv;

          $td = mcrypt_module_open('rijndael-128', ' ', 'cbc', $iv);

          mcrypt_generic_init($td, $this->key, $iv);
          $decrypted = mdecrypt_generic($td, $code);

          mcrypt_generic_deinit($td);
          mcrypt_module_close($td);

          return utf8_encode(trim($decrypted));
        }

        protected function hex2bin($hexdata) {
          $bindata = '';

          for ($i = 0; $i < strlen($hexdata); $i += 2) {
                $bindata .= chr(hexdec(substr($hexdata, $i, 2)));
          }

          return $bindata;
        }

}
