/*
 * 
 */
package com.serpro.library.String;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MCrypt {

        private String iv = "fedcba9876543210";//Dummy iv (CHANGE IT!)
        private IvParameterSpec ivspec;
        private SecretKeySpec keyspec;
        private Cipher cipher;

        private String SecretKey = "0123456789abcdef";//Dummy secretKey (CHANGE IT!)

        public MCrypt()
        {
                ivspec = new IvParameterSpec(iv.getBytes());

                keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");

                try {
                        cipher = Cipher.getInstance("AES/CBC/NoPadding");
                } catch (NoSuchAlgorithmException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }

        public byte[] encrypt(String text) throws Exception
        {
                if(text == null || text.length() == 0)
                        throw new Exception("Empty string");

                byte[] encrypted = null;

                try {
                        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);

                        encrypted = cipher.doFinal(padString(text).getBytes());
                } catch (Exception e)
                {                       
                        throw new Exception("[encrypt] " + e.getMessage());
                }

                return encrypted;
        }

        public byte[] decrypt(String code) throws Exception
        {
                if(code == null || code.length() == 0)
                        throw new Exception("Empty string");

                byte[] decrypted = null;

                try {
                        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

                        decrypted = cipher.doFinal(hexToBytes(code));
                } catch (Exception e)
                {
                        throw new Exception("[decrypt] " + e.getMessage());
                }
                return decrypted;
        }

	public static String bytesToHex(byte[] data)
	{
		char[] chars = new char[2 * data.length];
		for (int i = 0; i < data.length; ++i)
		{
			chars[2 * i] = HEX_CHARS[(data[i] & 0xF0) >>> 4];
			chars[2 * i + 1] = HEX_CHARS[data[i] & 0x0F];
		}
		return new String(chars);
	}



        public static byte[] hexToBytes(String str) {
                if (str==null) {
                        return null;
                } else if (str.length() < 2) {
                        return null;
                } else {
                        int len = str.length() / 2;
                        byte[] buffer = new byte[len];
                        for (int i=0; i<len; i++) {
                                buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
                        }
                        return buffer;
                }
        }



        private static String padString(String source)
        {
          char paddingChar = 0;
          int size = 16;
          int x = source.length() % size;
          int padLength = size - x;

          for (int i = 0; i < padLength; i++)
          {
                  source += paddingChar;
          }

          return source;
        }
}
