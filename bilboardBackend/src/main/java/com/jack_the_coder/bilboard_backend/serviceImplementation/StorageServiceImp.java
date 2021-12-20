package com.jack_the_coder.bilboard_backend.serviceImplementation;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.jack_the_coder.bilboard_backend.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImp implements StorageService {

    @Override
    public String save ( MultipartFile file , String fileType , String fileName ) {

        try {

            long millis = System.currentTimeMillis();
            AWSCredentials credentials =
                    new BasicAWSCredentials( "AKIAYZNHCDMMVNUAYCOY" , "Iv5QLogCJQlyx42xQjoQomsIMvphNilHSPS1P8ho" );
            AmazonS3 s3client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials( new AWSStaticCredentialsProvider( credentials ) )
                    .withRegion( Regions.EU_CENTRAL_1 )
                    .build();

            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength( IOUtils.toByteArray( file.getInputStream() ).length );

            /*
            s3client.putObject( new PutObjectRequest( "localization-bucket" , projectEntity.getCompany().getName() + "/"
                    + projectEntity.getName() + "/"
                    + millis + "." + file.getContentType().split( "/" )[ 1 ] , file.getInputStream() , meta )
                    .withCannedAcl( CannedAccessControlList.PublicRead ) );
             */

            String newFileName = fileType + "/" + fileName + "/" +
                    millis + "." + file.getContentType().split( "/" )[ 1 ];

            s3client.putObject(
                    new PutObjectRequest( "bilboard" , newFileName , file.getInputStream() , meta )
                            .withCannedAcl( CannedAccessControlList.PublicRead ) );

            //ObjectListing objectListing = s3client.listObjects( "localization-bucket" );

            return newFileName;
        } catch ( Exception e ) {
            throw new RuntimeException( "Could not store the file. Error: " + e.getMessage() );
        }
    }
}
