Android-PHP-Encrypt-Decrypt
===========================

Encrypt / Decrypt Between Android and PHP and vice-versa

######################
# HOW TO USE IT (JAVA)

mcrypt = new MCrypt();

/* Encrypt */

String encrypted = MCrypt.bytesToHex( mcrypt.encrypt("Text to Encrypt") );

/* Decrypt */

String decrypted = new String( mcrypt.decrypt( encrypted ) );

######################
# HOW TO USE IT (PHP)

$mcrypt = new MCrypt();

/* Encrypt */

$encrypted = $mcrypt->encrypt("Text to encrypt");

/* Decrypt */

$decrypted = $mcrypt->decrypt($encrypted);