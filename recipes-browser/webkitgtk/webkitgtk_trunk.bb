require webkitgtk.inc

DEFAULT_PRERENCE = "-1"

SVNBRANCH = "trunk"
SVNREV ?= "237403"

# To enable this recipe set on local.conf
# PREFERRED_VERSION_wpewebkit = "1.trunk%"
# To select a different SVN version you can also set on local.conf
# SVNREV = "$numberofsvnrev"

PV = "1.trunk-${SVNREV}"

SRC_URI = " \
    svn://svn.webkit.org/repository/webkit;module=${SVNBRANCH};rev=${SVNREV};protocol=https \
"
S = "${WORKDIR}/${SVNBRANCH}"

PACKAGECONFIG ?= " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl gles2 gst_gl webgl', '', d)} \
                   video \
                   webcrypto \
                   woff2 "
PACKAGECONFIG[webcrypto] = "-DENABLE_WEB_CRYPTO=ON,-DENABLE_WEB_CRYPTO=OFF,libgcrypt libtasn1"
PACKAGECONFIG[gst_gl] = "-DUSE_GSTREAMER_GL=ON,-DUSE_GSTREAMER_GL=OFF,gstreamer1.0-plugins-bad"
PACKAGECONFIG[woff2] = "-DUSE_WOFF2=ON,-DUSE_WOFF2=OFF,woff2"
