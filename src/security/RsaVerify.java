package security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class RsaVerify {
	
	public static boolean creatKey(int size, String prifile, String pubfile){
		if ((new java.io.File(prifile)).exists() == false
				&& (new java.io.File(pubfile)).exists() == false) {
			if (generatekey(size,prifile,pubfile) == false) {
				return false;
			}
			else return true;
		}
		else return false;
	}
	
	public static boolean sign(String s, String prifile, String sign) {
		boolean r=false;
		try {
			java.io.ObjectInputStream in = new java.io.ObjectInputStream(
					new java.io.FileInputStream(prifile));
			PrivateKey myprikey = (PrivateKey) in.readObject();
			in.close();

			Signature signet = Signature.getInstance("SHA1WithRSA");
			signet.initSign(myprikey);
			signet.update(s.getBytes());
			byte[] signed = signet.sign();
			
			//System.out.println("signed(ǩ������)="+FileDigest.byte2Str(signed));
			
			java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
					new java.io.FileOutputStream(sign));
			out.writeObject(signed);
			out.close();
			r=true;
			//System.out.println("ǩ���������ļ��ɹ�");
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			//System.out.println("ǩ���������ļ�ʧ��");
			r=false;
		}
		return r;
	}
	
	public static boolean checkSign(String s, String pubfile, String sign){
		boolean r=false;
		try {
			java.io.ObjectInputStream in = new java.io.ObjectInputStream(
					new java.io.FileInputStream(pubfile));
			PublicKey pubkey = (PublicKey) in.readObject();
			in.close();
			
			//System.out.println(pubkey.getFormat());
			
			in = new java.io.ObjectInputStream(new java.io.FileInputStream(sign));
			byte[] signed = (byte[]) in.readObject();
			in.close();

			Signature signetcheck = Signature.getInstance("SHA1WithRSA");
			signetcheck.initVerify(pubkey);
			signetcheck.update(s.getBytes());
			if (signetcheck.verify(signed)) {
				//System.out.println("info=" + s);
				//System.out.println("ǩ������");
				r=true;
			} else{
				//System.out.println("��ǩ������");
				r=false;
			}
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			System.out.println("Error: checkSign failed");
		}
		return r;
	}
	
	public static boolean generatekey(int size, String prifile, String pubfile) {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			keygen.initialize(size);
			KeyPair keys=keygen.generateKeyPair();
			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();

			java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
					new java.io.FileOutputStream(prifile));
			out.writeObject((Object)prikey);
			out.close();
			//System.out.println("д����� prikeys ok");
			out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(pubfile));
			out.writeObject((Object)pubkey);
			out.close();
			//System.out.println("д����� pubkeys ok");
			return true;
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			//System.out.println("������Կ��ʧ��");
			return false;
		}
	}
}

