

String imagePath = "";
Integer REQUEST_CAMERA = 1;
 
public void CaptureImage() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.i(TAG, "IOException");
            }
            if (photoFile != null) {
                imagePath = photoFile.getAbsolutePath();
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent, REQUEST_CAMERA);
            }
        }
    }

 public File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

 @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
               setImage(context, imagePath, assetImage);
            }
        }
    }


 public void setImage(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
//   Use these line is you want a Place holder or error holder on image not availble
   
//         requestOptions.placeholder(R.drawable.img_placeholder);
//         requestOptions.error(R.drawable.img_placeholder);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        if (url != null)
        {
                Glide.with(context).load(url).apply(requestOptions).into(imageView);
            }
        }
    }
