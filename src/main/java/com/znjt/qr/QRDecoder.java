package com.znjt.qr;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.detector.Detector;

import java.util.Map;

/**
 * Created on 2019-05-29 22:56
 * ClassName:QRDecoder
 * Package:com.znjt.qr
 * Company FREEDOM
 * Description:
 * Depart Tech
 *
 * @author qiury_zhenxin@126.com
 */
public class QRDecoder extends QRCodeReader {
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];

    private static final Decoder decoder = new Decoder();

    public static DetectorResult decodeQR(BinaryBitmap image, Map<DecodeHintType,?> hints)
            throws NotFoundException, ChecksumException, FormatException {
        DetectorResult detectorResult = new Detector(image.getBlackMatrix()).detect(hints);
        return detectorResult;
    }
    /**
     * This method detects a code in a "pure" image -- that is, pure monochrome image
     * which contains only an unrotated, unskewed, image of a code, with some white border
     * around it. This is a specialized method that works exceptionally fast in this special
     * case.
     */
    private static BitMatrix extractPureBits(BitMatrix image) throws NotFoundException {

        int[] leftTopBlack = image.getTopLeftOnBit();
        int[] rightBottomBlack = image.getBottomRightOnBit();
        if (leftTopBlack == null || rightBottomBlack == null) {
            throw NotFoundException.getNotFoundInstance();
        }

        float moduleSize = moduleSize(leftTopBlack, image);

        int top = leftTopBlack[1];
        int bottom = rightBottomBlack[1];
        int left = leftTopBlack[0];
        int right = rightBottomBlack[0];

        // Sanity check!
        if (left >= right || top >= bottom) {
            throw NotFoundException.getNotFoundInstance();
        }

        if (bottom - top != right - left) {
            // Special case, where bottom-right module wasn't black so we found something else in the last row
            // Assume it's a square, so use height as the width
            right = left + (bottom - top);
            if (right >= image.getWidth()) {
                // Abort if that would not make sense -- off image
                throw NotFoundException.getNotFoundInstance();
            }
        }

        int matrixWidth = Math.round((right - left + 1) / moduleSize);
        int matrixHeight = Math.round((bottom - top + 1) / moduleSize);
        if (matrixWidth <= 0 || matrixHeight <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (matrixHeight != matrixWidth) {
            // Only possibly decode square regions
            throw NotFoundException.getNotFoundInstance();
        }

        // Push in the "border" by half the module width so that we start
        // sampling in the middle of the module. Just in case the image is a
        // little off, this will help recover.
        int nudge = (int) (moduleSize / 2.0f);
        top += nudge;
        left += nudge;

        // But careful that this does not sample off the edge
        // "right" is the farthest-right valid pixel location -- right+1 is not necessarily
        // This is positive by how much the inner x loop below would be too large
        int nudgedTooFarRight = left + (int) ((matrixWidth - 1) * moduleSize) - right;
        if (nudgedTooFarRight > 0) {
            if (nudgedTooFarRight > nudge) {
                // Neither way fits; abort
                throw NotFoundException.getNotFoundInstance();
            }
            left -= nudgedTooFarRight;
        }
        // See logic above
        int nudgedTooFarDown = top + (int) ((matrixHeight - 1) * moduleSize) - bottom;
        if (nudgedTooFarDown > 0) {
            if (nudgedTooFarDown > nudge) {
                // Neither way fits; abort
                throw NotFoundException.getNotFoundInstance();
            }
            top -= nudgedTooFarDown;
        }

        // Now just read off the bits
        BitMatrix bits = new BitMatrix(matrixWidth, matrixHeight);
        for (int y = 0; y < matrixHeight; y++) {
            int iOffset = top + (int) (y * moduleSize);
            for (int x = 0; x < matrixWidth; x++) {
                if (image.get(left + (int) (x * moduleSize), iOffset)) {
                    bits.set(x, y);
                }
            }
        }
        return bits;
    }

    private static float moduleSize(int[] leftTopBlack, BitMatrix image) throws NotFoundException {
        int height = image.getHeight();
        int width = image.getWidth();
        int x = leftTopBlack[0];
        int y = leftTopBlack[1];
        boolean inBlack = true;
        int transitions = 0;
        while (x < width && y < height) {
            if (inBlack != image.get(x, y)) {
                if (++transitions == 5) {
                    break;
                }
                inBlack = !inBlack;
            }
            x++;
            y++;
        }
        if (x == width || y == height) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (x - leftTopBlack[0]) / 7.0f;
    }
}
