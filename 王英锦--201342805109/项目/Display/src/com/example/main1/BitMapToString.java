package com.example.main1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;

public class BitMapToString {
	
	public static byte[] bitmapToString(Bitmap bitmap) {
		byte[] string = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
		string = byteArrayOutputStream.toByteArray();
		return string;
	}

	public static Bitmap StringToBitmap(byte[] bytes) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
		return bitmap;
	}
}
