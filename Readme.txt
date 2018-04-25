===============================================
							ArkavQuarium
===============================================
File-file source code berada pada folder src.
Jika Anda ingin memindahkan file-file tersebut ke folder lain, pindahkan seluruh isi dari folder src ke folder tujuan yang diinginkan.

Untuk menjalankan program, pastikan komputer Anda memiliki Java JDK minimal versi 8.
Kemudian, Anda dapat mengikuti langkah-langkah berikut :
1. Buka terminal/command prompt/shell. Pindahkan current directory ke folder src
2. Compile program dengan Makefile (dengan perintah "make" , tanpa petik), atau dapat menggunakan perintah :
	javac -cp "arkavquarium/junit-4.12.jar" arkavquarium/*.java arkavquarium/lib/*.java
3. Jalankan program dengan perintah : java arkavquarium.Main

Untuk membersihkan folder src dari file-file binary hasil kompilasi, dapat digunakan perintah "make clean", atau
secara manual, perintah berikut dapat digunakan :
	rm arkavquarium/*.class
	rm arkavquarium/lib/*.class

Selamat bermain!
