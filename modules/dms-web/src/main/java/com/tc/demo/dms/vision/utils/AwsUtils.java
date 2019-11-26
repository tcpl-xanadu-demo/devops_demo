package com.tc.demo.dms.vision.utils;

public class AwsUtils {
	/*public AmazonRekognition getRekognitionClient() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAURF4HATTRIDEOP56",
				"0RBAkxdxweZOicxMD2z0inyg3EAbDlGoDcUb3UNl");

		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();

		return rekognitionClient;
	}*/
/*	public void convertImageToText(String bucket, String photo, AmazonRekognition rekognitionClient) {

		DetectTextRequest request = new DetectTextRequest()
				.withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)));

		try {
			DetectTextResult result = rekognitionClient.detectText(request);
			List<TextDetection> textDetections = result.getTextDetections();

			System.out.println("Detected lines and words for " + photo);
			for (TextDetection text : textDetections) {

				System.out.println("Detected: " + text.getDetectedText());
				System.out.println("Confidence: " + text.getConfidence().toString());
				System.out.println("Id : " + text.getId());
				System.out.println("Parent Id: " + text.getParentId());
				System.out.println("Type: " + text.getType());
				System.out.println();
			}
		} catch (AmazonRekognitionException e) {
			e.printStackTrace();
		}
	}*/
}
