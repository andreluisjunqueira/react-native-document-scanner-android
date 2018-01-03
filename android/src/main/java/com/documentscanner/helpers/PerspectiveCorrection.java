package com.documentscanner.helpers;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andre on 13/12/2017.
 */

public class PerspectiveCorrection {


    public static Mat correctPerspective(String fileName) {


        Mat imgSource = Imgcodecs.imread(fileName, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        // convert the image to black and white does (8 bit)
        Imgproc.Canny(imgSource.clone(), imgSource, 50, 50);

        // apply gaussian blur to smoothen lines of dots
        Imgproc.GaussianBlur(imgSource, imgSource, new org.opencv.core.Size(5, 5), 5);

        // find the contours
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(imgSource, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        double maxArea = -1;
        MatOfPoint temp_contour = contours.get(0); // the largest is at the
        // index 0 for starting
        // point
        MatOfPoint2f approxCurve = new MatOfPoint2f();

        for (int idx = 0; idx < contours.size(); idx++) {
            temp_contour = contours.get(idx);
            double contourarea = Imgproc.contourArea(temp_contour);
            // compare this contour to the previous largest contour found
            if (contourarea > maxArea) {
                // check if this contour is a square
                MatOfPoint2f new_mat = new MatOfPoint2f(temp_contour.toArray());
                int contourSize = (int) temp_contour.total();
                MatOfPoint2f approxCurve_temp = new MatOfPoint2f();
                Imgproc.approxPolyDP(new_mat, approxCurve_temp, contourSize * 0.05, true);
                if (approxCurve_temp.total() == 4) {
                    maxArea = contourarea;
                    approxCurve = approxCurve_temp;
                }
            }
        }

        Imgproc.cvtColor(imgSource, imgSource, Imgproc.COLOR_BayerBG2RGB);
        Mat sourceImage = Imgcodecs.imread(fileName, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        double[] temp_double;
        temp_double = approxCurve.get(0, 0);
        Point p1 = new Point(temp_double[0], temp_double[1]);
        // Core.circle(imgSource,p1,55,new Scalar(0,0,255));
        // Imgproc.warpAffine(sourceImage, dummy, rotImage,sourceImage.size());
        temp_double = approxCurve.get(1, 0);
        Point p2 = new Point(temp_double[0], temp_double[1]);
        // Core.circle(imgSource,p2,150,new Scalar(255,255,255));
        temp_double = approxCurve.get(2, 0);
        Point p3 = new Point(temp_double[0], temp_double[1]);
        // Core.circle(imgSource,p3,200,new Scalar(255,0,0));
        temp_double = approxCurve.get(3, 0);
        Point p4 = new Point(temp_double[0], temp_double[1]);
        // Core.circle(imgSource,p4,100,new Scalar(0,0,255));
        List<Point> source = new ArrayList<Point>();
        source.add(p1);
        source.add(p2);
        source.add(p3);
        source.add(p4);
        Mat startM = Converters.vector_Point2f_to_Mat(source);
        Mat result = warp(sourceImage, startM);

        Imgcodecs.imwrite(fileName+"/IMG_corrected.jpg", result);
        return result;

    }

    public static Mat warp(Mat inputMat, Mat startM) {

        int resultWidth = 1200;
        int resultHeight = 680;

        Point ocvPOut4 = new Point(0, 0);
        Point ocvPOut1 = new Point(0, resultHeight);
        Point ocvPOut2 = new Point(resultWidth, resultHeight);
        Point ocvPOut3 = new Point(resultWidth, 0);

        if (inputMat.height() > inputMat.width()) {
            // int temp = resultWidth;
            // resultWidth = resultHeight;
            // resultHeight = temp;

            ocvPOut3 = new Point(0, 0);
            ocvPOut4 = new Point(0, resultHeight);
            ocvPOut1 = new Point(resultWidth, resultHeight);
            ocvPOut2 = new Point(resultWidth, 0);
        }

        Mat outputMat = new Mat(resultWidth, resultHeight, CvType.CV_8UC4);

        List<Point> dest = new ArrayList<Point>();
        dest.add(ocvPOut1);
        dest.add(ocvPOut2);
        dest.add(ocvPOut3);
        dest.add(ocvPOut4);

        Mat endM = Converters.vector_Point2f_to_Mat(dest);

        Mat perspectiveTransform = Imgproc.getPerspectiveTransform(startM, endM);

        Imgproc.warpPerspective(inputMat, outputMat, perspectiveTransform, new Size(resultWidth, resultHeight), Imgproc.INTER_CUBIC);

        return outputMat;
    }
}
