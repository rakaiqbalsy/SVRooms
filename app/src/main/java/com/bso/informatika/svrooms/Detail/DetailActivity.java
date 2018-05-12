package com.bso.informatika.svrooms.Detail;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bso.informatika.svrooms.R;
import com.bso.informatika.svrooms.uploadActivity.upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mUploadImage;
    private Button mSimpanData;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private EditText idHarga;
    private EditText idAlamat;
    private EditText idDeskripsi;
    private EditText idRuangan;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mUploadImage = findViewById(R.id.uploadImage);
        mSimpanData = findViewById(R.id.simpandata);
        mImageView = findViewById(R.id.imageView);
        mProgressBar = findViewById(R.id.progressupload);
        idHarga = findViewById(R.id.idHarga);
        idAlamat = findViewById(R.id.idAlamat);
        idDeskripsi = findViewById(R.id.idDeskripsi);
        idRuangan = findViewById(R.id.idRuangan);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");


        mUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        mSimpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(DetailActivity.this, "Proses Upload", Toast.LENGTH_SHORT).show();
                }
                simpanFile();
            }
        });
    }

    private void openFileChooser() {
        Intent i = new Intent();
        i.setType("image/* ");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK && data != null
                && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void simpanFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
            +"."+getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 5000);

                            Toast.makeText(DetailActivity.this, "Upload Berhasil", Toast.LENGTH_LONG).show();
                            upload Upload = new upload(idRuangan.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());
                            String UploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(UploadId).setValue(Upload);
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(DetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress); //casting double ke integer
                        }
                    });
        }
        else{
            Toast.makeText(this, "Gambar tidak terpilih", Toast.LENGTH_SHORT).show();
        }
    }
}