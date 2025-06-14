#!/usr/bin/env python
# -*- coding: utf-8 -*-

# This script takes in an image and converts it to 3 output images
# to 3 different sizes and resolutions (mdpi, hdpi and xhdpi).
# The conversion tool used is ImageMagick.

import os
import sys

MDPI = 160
HDPI = 240
XHDPI = 320

def convert_dp_to_pixel(dp, dpi):
    return int(dp * dpi / 160)


def prepare_cmd(dpi, px):
    dst_img = '{0}_{1}dpi.png'.format(os.path.splitext(src_img)[0], dpi)
    cmd = u'convert -verbose {0} -background none -density {2} ' \
        '-resize {3}x{3} {1}'.format(src_img, dst_img, dpi, px)
    return cmd


def create_images():
    mdpi_size = convert_dp_to_pixel(out_dp, MDPI)
    hdpi_size = convert_dp_to_pixel(out_dp, HDPI)
    xhdpi_size = convert_dp_to_pixel(out_dp, XHDPI)

    cmd_mdpi = prepare_cmd(MDPI, mdpi_size)
    cmd_hdpi = prepare_cmd(HDPI, hdpi_size)
    cmd_xhdpi = prepare_cmd(XHDPI, xhdpi_size)
    
    print( cmd_mdpi)
    os.system(cmd_mdpi)
    print( cmd_hdpi)
    os.system(cmd_hdpi)
    print(cmd_xhdpi)
    os.system(cmd_xhdpi)


if __name__ == "__main__":

    arg = sys.argv[1:]
    if len(arg) != 2:
        print( 'Usage: python %s [source_img] [output_size_in_dp]' % sys.argv[0])
        sys.exit(0)

    global src_img
    src_img = sys.argv[1]
    global out_dp
    out_dp = int(sys.argv[2])

    create_images()
