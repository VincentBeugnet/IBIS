// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: answer.proto

// Protobuf Java Version: 3.25.1
package com.thalesgroup.ibis.protos;

public final class AnswerOuterClass {
  private AnswerOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibis_Angle_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibis_Angle_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibis_Velocity_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibis_Velocity_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibis_Distance_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibis_Distance_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibis_Dimension_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibis_Dimension_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibis_RadarCrossSection_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibis_RadarCrossSection_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ibis_Answer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ibis_Answer_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014answer.proto\022\004ibis\"1\n\005Angle\022\r\n\005angle\030\001" +
      " \001(\002\022\031\n\021angle_uncertainty\030\002 \001(\002\":\n\010Veloc" +
      "ity\022\020\n\010velocity\030\001 \001(\002\022\034\n\024velocity_uncert" +
      "ainty\030\002 \001(\002\":\n\010Distance\022\020\n\010distance\030\001 \001(" +
      "\002\022\034\n\024distance_uncertainty\030\002 \001(\002\"=\n\tDimen" +
      "sion\022\021\n\tdimension\030\001 \001(\002\022\035\n\025dimension_unc" +
      "ertainty\030\002 \001(\002\"U\n\021RadarCrossSection\022\031\n\021r" +
      "adarCrossSection\030\001 \001(\002\022%\n\035radarCrossSect" +
      "ion_uncertainty\030\002 \001(\002\"\310\004\n\006Answer\022\020\n\010trac" +
      "k_id\030\001 \001(\t\022\035\n\025source_sensor_mode_id\030\002 \001(" +
      "\t\022\021\n\ttimestamp\030\003 \001(\003\022\022\n\ntime_taken\030\004 \001(\003" +
      "\022\035\n\010latitude\030\005 \001(\0132\013.ibis.Angle\022\036\n\tlongi" +
      "tude\030\006 \001(\0132\013.ibis.Angle\022!\n\televation\030\007 \001" +
      "(\0132\016.ibis.Distance\022 \n\010distance\030\010 \001(\0132\016.i" +
      "bis.Distance\022\037\n\006height\030\t \001(\0132\017.ibis.Dime" +
      "nsion\022\037\n\006length\030\n \001(\0132\017.ibis.Dimension\022\036" +
      "\n\005width\030\013 \001(\0132\017.ibis.Dimension\022 \n\010veloci" +
      "ty\030\014 \001(\0132\016.ibis.Velocity\0222\n\021radarCrossSe" +
      "ction\030\r \001(\0132\027.ibis.RadarCrossSection\022\030\n\013" +
      "object_type\030\016 \001(\tH\000\210\001\001\022\"\n\025object_identif" +
      "ication\030\017 \001(\tH\001\210\001\001\022%\n\030waveform_caracteri" +
      "sation\030\020 \001(\tH\002\210\001\001B\016\n\014_object_typeB\030\n\026_ob" +
      "ject_identificationB\033\n\031_waveform_caracte" +
      "risationB\037\n\033com.thalesgroup.ibis.protosP" +
      "\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_ibis_Angle_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ibis_Angle_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibis_Angle_descriptor,
        new java.lang.String[] { "Angle", "AngleUncertainty", });
    internal_static_ibis_Velocity_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_ibis_Velocity_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibis_Velocity_descriptor,
        new java.lang.String[] { "Velocity", "VelocityUncertainty", });
    internal_static_ibis_Distance_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_ibis_Distance_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibis_Distance_descriptor,
        new java.lang.String[] { "Distance", "DistanceUncertainty", });
    internal_static_ibis_Dimension_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_ibis_Dimension_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibis_Dimension_descriptor,
        new java.lang.String[] { "Dimension", "DimensionUncertainty", });
    internal_static_ibis_RadarCrossSection_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_ibis_RadarCrossSection_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibis_RadarCrossSection_descriptor,
        new java.lang.String[] { "RadarCrossSection", "RadarCrossSectionUncertainty", });
    internal_static_ibis_Answer_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_ibis_Answer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ibis_Answer_descriptor,
        new java.lang.String[] { "TrackId", "SourceSensorModeId", "Timestamp", "TimeTaken", "Latitude", "Longitude", "Elevation", "Distance", "Height", "Length", "Width", "Velocity", "RadarCrossSection", "ObjectType", "ObjectIdentification", "WaveformCaracterisation", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
