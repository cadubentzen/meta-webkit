require webkitgtk.inc

DEPENDS += " libgcrypt"

SRC_URI = "\
   https://www.webkitgtk.org/releases/webkitgtk-${PV}.tar.xz \
"

SRC_URI[md5sum] = "6dae6837c884a25413fb1d4527c9894a"
SRC_URI[sha256sum] = "9dfd542902953be9af8ff32bd37fe662ca3d75f7348514ebac15c6252a6ccd72"

PACKAGECONFIG ?= " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl gles2 gst_gl webgl', '', d)} \
                   video \
                   webcrypto \
                   woff2 "
PACKAGECONFIG[webcrypto] = "-DENABLE_WEB_CRYPTO=ON,-DENABLE_WEB_CRYPTO=OFF,libgcrypt libtasn1"
PACKAGECONFIG[gst_gl] = "-DUSE_GSTREAMER_GL=ON,-DUSE_GSTREAMER_GL=OFF,gstreamer1.0-plugins-bad"
PACKAGECONFIG[woff2] = "-DUSE_WOFF2=ON,-DUSE_WOFF2=OFF,woff2"
