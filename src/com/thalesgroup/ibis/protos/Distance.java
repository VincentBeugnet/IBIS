// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: answer.proto

// Protobuf Java Version: 3.25.1
package com.thalesgroup.ibis.protos;

/**
 * Protobuf type {@code ibis.Distance}
 */
public final class Distance extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ibis.Distance)
    DistanceOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Distance.newBuilder() to construct.
  private Distance(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Distance() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Distance();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Distance_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Distance_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.thalesgroup.ibis.protos.Distance.class, com.thalesgroup.ibis.protos.Distance.Builder.class);
  }

  public static final int DISTANCE_FIELD_NUMBER = 1;
  private float distance_ = 0F;
  /**
   * <pre>
   * Distance (m)
   * </pre>
   *
   * <code>float distance = 1;</code>
   * @return The distance.
   */
  @java.lang.Override
  public float getDistance() {
    return distance_;
  }

  public static final int DISTANCE_UNCERTAINTY_FIELD_NUMBER = 2;
  private float distanceUncertainty_ = 0F;
  /**
   * <pre>
   * Incertitude distance
   * </pre>
   *
   * <code>float distance_uncertainty = 2;</code>
   * @return The distanceUncertainty.
   */
  @java.lang.Override
  public float getDistanceUncertainty() {
    return distanceUncertainty_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (java.lang.Float.floatToRawIntBits(distance_) != 0) {
      output.writeFloat(1, distance_);
    }
    if (java.lang.Float.floatToRawIntBits(distanceUncertainty_) != 0) {
      output.writeFloat(2, distanceUncertainty_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (java.lang.Float.floatToRawIntBits(distance_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(1, distance_);
    }
    if (java.lang.Float.floatToRawIntBits(distanceUncertainty_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(2, distanceUncertainty_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.thalesgroup.ibis.protos.Distance)) {
      return super.equals(obj);
    }
    com.thalesgroup.ibis.protos.Distance other = (com.thalesgroup.ibis.protos.Distance) obj;

    if (java.lang.Float.floatToIntBits(getDistance())
        != java.lang.Float.floatToIntBits(
            other.getDistance())) return false;
    if (java.lang.Float.floatToIntBits(getDistanceUncertainty())
        != java.lang.Float.floatToIntBits(
            other.getDistanceUncertainty())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DISTANCE_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getDistance());
    hash = (37 * hash) + DISTANCE_UNCERTAINTY_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getDistanceUncertainty());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.thalesgroup.ibis.protos.Distance parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.thalesgroup.ibis.protos.Distance parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thalesgroup.ibis.protos.Distance parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.thalesgroup.ibis.protos.Distance prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ibis.Distance}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ibis.Distance)
      com.thalesgroup.ibis.protos.DistanceOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Distance_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Distance_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.thalesgroup.ibis.protos.Distance.class, com.thalesgroup.ibis.protos.Distance.Builder.class);
    }

    // Construct using com.thalesgroup.ibis.protos.Distance.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      distance_ = 0F;
      distanceUncertainty_ = 0F;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Distance_descriptor;
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Distance getDefaultInstanceForType() {
      return com.thalesgroup.ibis.protos.Distance.getDefaultInstance();
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Distance build() {
      com.thalesgroup.ibis.protos.Distance result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Distance buildPartial() {
      com.thalesgroup.ibis.protos.Distance result = new com.thalesgroup.ibis.protos.Distance(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.thalesgroup.ibis.protos.Distance result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.distance_ = distance_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.distanceUncertainty_ = distanceUncertainty_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.thalesgroup.ibis.protos.Distance) {
        return mergeFrom((com.thalesgroup.ibis.protos.Distance)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.thalesgroup.ibis.protos.Distance other) {
      if (other == com.thalesgroup.ibis.protos.Distance.getDefaultInstance()) return this;
      if (other.getDistance() != 0F) {
        setDistance(other.getDistance());
      }
      if (other.getDistanceUncertainty() != 0F) {
        setDistanceUncertainty(other.getDistanceUncertainty());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 13: {
              distance_ = input.readFloat();
              bitField0_ |= 0x00000001;
              break;
            } // case 13
            case 21: {
              distanceUncertainty_ = input.readFloat();
              bitField0_ |= 0x00000002;
              break;
            } // case 21
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private float distance_ ;
    /**
     * <pre>
     * Distance (m)
     * </pre>
     *
     * <code>float distance = 1;</code>
     * @return The distance.
     */
    @java.lang.Override
    public float getDistance() {
      return distance_;
    }
    /**
     * <pre>
     * Distance (m)
     * </pre>
     *
     * <code>float distance = 1;</code>
     * @param value The distance to set.
     * @return This builder for chaining.
     */
    public Builder setDistance(float value) {

      distance_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Distance (m)
     * </pre>
     *
     * <code>float distance = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDistance() {
      bitField0_ = (bitField0_ & ~0x00000001);
      distance_ = 0F;
      onChanged();
      return this;
    }

    private float distanceUncertainty_ ;
    /**
     * <pre>
     * Incertitude distance
     * </pre>
     *
     * <code>float distance_uncertainty = 2;</code>
     * @return The distanceUncertainty.
     */
    @java.lang.Override
    public float getDistanceUncertainty() {
      return distanceUncertainty_;
    }
    /**
     * <pre>
     * Incertitude distance
     * </pre>
     *
     * <code>float distance_uncertainty = 2;</code>
     * @param value The distanceUncertainty to set.
     * @return This builder for chaining.
     */
    public Builder setDistanceUncertainty(float value) {

      distanceUncertainty_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Incertitude distance
     * </pre>
     *
     * <code>float distance_uncertainty = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearDistanceUncertainty() {
      bitField0_ = (bitField0_ & ~0x00000002);
      distanceUncertainty_ = 0F;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:ibis.Distance)
  }

  // @@protoc_insertion_point(class_scope:ibis.Distance)
  private static final com.thalesgroup.ibis.protos.Distance DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.thalesgroup.ibis.protos.Distance();
  }

  public static com.thalesgroup.ibis.protos.Distance getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Distance>
      PARSER = new com.google.protobuf.AbstractParser<Distance>() {
    @java.lang.Override
    public Distance parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Distance> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Distance> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.thalesgroup.ibis.protos.Distance getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

