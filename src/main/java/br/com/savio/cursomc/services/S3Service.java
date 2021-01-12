package br.com.savio.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public void uploadFile(String localFilePath) {
		try {

			LOG.info("Iniciando Upload");
			File file = new File(localFilePath);
			s3client.putObject(new PutObjectRequest(bucketName, "teste.png", file));
			LOG.info("Upload finalizado");

		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException :" + e.getErrorMessage());
			LOG.info("Status code: " + e.getStatusCode());
		} catch (AmazonClientException e) {
			LOG.info("AmazonClientException :" + e.getMessage());
		}

	}
}