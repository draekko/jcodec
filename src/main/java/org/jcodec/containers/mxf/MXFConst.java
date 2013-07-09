package org.jcodec.containers.mxf;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.jcodec.common.Codec;
import org.jcodec.containers.mxf.model.AES3PCMDescriptor;
import org.jcodec.containers.mxf.model.CDCIEssenceDescriptor;
import org.jcodec.containers.mxf.model.ContentStorage;
import org.jcodec.containers.mxf.model.EssenceContainerData;
import org.jcodec.containers.mxf.model.FileDescriptor;
import org.jcodec.containers.mxf.model.GenericDataEssenceDescriptor;
import org.jcodec.containers.mxf.model.GenericDescriptor;
import org.jcodec.containers.mxf.model.GenericPictureEssenceDescriptor;
import org.jcodec.containers.mxf.model.GenericSoundEssenceDescriptor;
import org.jcodec.containers.mxf.model.Identification;
import org.jcodec.containers.mxf.model.IndexSegment;
import org.jcodec.containers.mxf.model.J2KPictureDescriptor;
import org.jcodec.containers.mxf.model.MPEG2VideoDescriptor;
import org.jcodec.containers.mxf.model.MXFMetadata;
import org.jcodec.containers.mxf.model.MXFPartitionPack;
import org.jcodec.containers.mxf.model.MaterialPackage;
import org.jcodec.containers.mxf.model.Preface;
import org.jcodec.containers.mxf.model.RGBAEssenceDescriptor;
import org.jcodec.containers.mxf.model.Sequence;
import org.jcodec.containers.mxf.model.SourceClip;
import org.jcodec.containers.mxf.model.SourcePackage;
import org.jcodec.containers.mxf.model.TimecodeComponent;
import org.jcodec.containers.mxf.model.TimelineTrack;
import org.jcodec.containers.mxf.model.UL;
import org.jcodec.containers.mxf.model.WaveAudioDescriptor;

/**
 * This class is part of JCodec ( www.jcodec.org ) This software is distributed
 * under FreeBSD License
 * 
 * @author The JCodec project
 * 
 */
public class MXFConst {

    public static UL HEADER_PARTITION_KLV = new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02,
            0x01, 0x01, 0x02);

    public static UL INDEX_KLV = new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
            0x10, 0x01, 0x00);

    public static UL GENERIC_DESCRIPTOR_KLV = new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01,
            0x01, 0x01, 0x01);

    public static enum MXFCodecMapping {

        MPEG2_ML(
                new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x03, 0x04, 0x01, 0x02, 0x02, 0x01, 0x01, 0x11, 0x00),
                Codec.MPEG2),

        MPEG2_D10_PAL(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x01, 0x02, 0x02, 0x01, 0x02, 0x01,
                0x01), Codec.MPEG2),

        MPEG2_HL(
                new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x03, 0x04, 0x01, 0x02, 0x02, 0x01, 0x03, 0x03, 0x00),
                Codec.MPEG2),

        MPEG2_HL_422_I(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x03, 0x04, 0x01, 0x02, 0x02, 0x01, 0x04, 0x02,
                0x00), Codec.MPEG2),

        MPEG4_XDCAM_PROXY(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x03, 0x04, 0x01, 0x02, 0x02, 0x01, 0x20,
                0x02, 0x03), Codec.MPEG4),

        DV_25_PAL(
                new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x01, 0x02, 0x02, 0x02, 0x01, 0x02, 0x00),
                Codec.DV),

        JPEG2000(
                new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x07, 0x04, 0x01, 0x02, 0x02, 0x03, 0x01, 0x01, 0x00),
                Codec.J2K),

        RAW(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x01, 0x02, 0x01, 0x7F, 0x00, 0x00, 0x00),
                null),

        VC3_DNXD(
                new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x01, 0x02, 0x02, 0x03, 0x02, 0x00, 0x00),
                Codec.VC3),

        AVC_INTRA(
                new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x0A, 0x04, 0x01, 0x02, 0x02, 0x01, 0x32, 0x00, 0x00),
                Codec.H264),

        V210(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x0A, 0x04, 0x01, 0x02, 0x01, 0x01, 0x02, 0x02, 0x00),
                Codec.V210),

        PCM_S16LE_1(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x02, 0x02, 0x01, 0x00), null),

        PCM_S16LE_3(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x02, 0x02, 0x01, 0x01), null),

        PCM_S16LE_2(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x02, 0x02, 0x01, 0x7F), null),

        PCM_S16BE(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x07, 0x04, 0x02, 0x02, 0x01, 0x7E), null),

        PCM_ALAW(
                new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x04, 0x04, 0x02, 0x02, 0x02, 0x03, 0x01, 0x01, 0x00),
                Codec.ALAW),

        AC3(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x02, 0x02, 0x02, 0x03, 0x02, 0x01, 0x00),
                Codec.AC3),

        MP2(new UL(0x06, 0x0E, 0x2B, 0x34, 0x04, 0x01, 0x01, 0x01, 0x04, 0x02, 0x02, 0x02, 0x03, 0x02, 0x05, 0x00),
                Codec.MP3);

        private UL ul;
        private Codec codec;

        private MXFCodecMapping(UL ul, Codec codec) {
            this.ul = ul;
            this.codec = codec;
        }

        public UL getUl() {
            return ul;
        }

        public Codec getCodec() {
            return codec;
        }
    };

    public static Map<UL, Class<? extends MXFMetadata>> klMetadataMapping = new HashMap<UL, Class<? extends MXFMetadata>>();

    static {
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x18, 0x00), ContentStorage.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x37, 0x00), SourcePackage.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x0F, 0x00), Sequence.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0D, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x2F, 0x00), Preface.class);
        klMetadataMapping.put(new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x30, 0x00), Identification.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x11, 0x00), SourceClip.class);
        klMetadataMapping.put(new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x23, 0x00), EssenceContainerData.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x3A, 0x00), TimelineTrack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x3B, 0x00), TimelineTrack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x36, 0x00), MaterialPackage.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x10, 0x01, 0x00), IndexSegment.class);

        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x44, 0x00), GenericDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x5b, 0x00), GenericDataEssenceDescriptor.class);
        klMetadataMapping.put(new UL(06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01, 0x01,
                0x5b, 0x00), GenericDataEssenceDescriptor.class);
        klMetadataMapping.put(new UL(06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01, 0x01,
                0x5c, 0x00), GenericDataEssenceDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x43, 0x00), GenericDataEssenceDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x42, 0x00), GenericSoundEssenceDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x28, 0x00), CDCIEssenceDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x29, 0x00), RGBAEssenceDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x51, 0x00), MPEG2VideoDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x48, 0x00), WaveAudioDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x25, 0x00), FileDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x27, 0x00), GenericPictureEssenceDescriptor.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x47, 0x00), AES3PCMDescriptor.class);
        
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x05, 0x01, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x02, 0x01, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x02, 0x02, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x02, 0x03, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x02, 0x04, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x03, 0x01, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x03, 0x02, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x03, 0x03, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x03, 0x04, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x04, 0x02, 0x00), MXFPartitionPack.class);
        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x05, 0x01, 0x01, 0x0d, 0x01, 0x02, 0x01, 0x01,
                0x04, 0x04, 0x00), MXFPartitionPack.class);

        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0D, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x14, 0x00), TimecodeComponent.class);

        klMetadataMapping.put(new UL(0x06, 0x0E, 0x2B, 0x34, 0x01, 0x01, 0x01, 0x02, 0x03, 0x01, 0x02, 0x10, 0x01,
                0x00, 0x00, 0x00), KLVFill.class);

        klMetadataMapping.put(new UL(0x06, 0x0e, 0x2b, 0x34, 0x02, 0x53, 0x01, 0x01, 0x0d, 0x01, 0x01, 0x01, 0x01,
                0x01, 0x5a, 0x00), J2KPictureDescriptor.class);

    }

    public static class KLVFill extends MXFMetadata {
        public KLVFill(UL ul) {
            super(ul);
        }

        public void read(ByteBuffer bb) {
        }
    }
}
