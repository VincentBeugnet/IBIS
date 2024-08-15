// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: answer.proto

// Protobuf Java Version: 3.25.1
package com.thalesgroup.ibis.protos;

/**
 * Protobuf type {@code ibis.Velocity}
 */
public final class Velocity extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ibis.Velocity)
    VelocityOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Velocity.newBuilder() to construct.
  private Velocity(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Velocity() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Velocity();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Velocity_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Velocity_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.thalesgroup.ibis.protos.Velocity.class, com.thalesgroup.ibis.protos.Velocity.Builder.class);
  }

  public static final int VELOCITY_FIELD_NUMBER = 1;
  private float velocity_ = 0F;
  /**
   * <pre>
   * Vitesse de la piste 
   * </pre>
   *
   * <code>float velocity = 1;</code>
   * @return The velocity.
   */
  @java.lang.Override
  public float getVelocity() {
    return velocity_;
  }

  public static final int VELOCITY_UNCERTAINTY_FIELD_NUMBER = 2;
  private float velocityUncertainty_ = 0F;
  /**
   * <pre>
   * Incertitude vitesse
   * </pre>
   *
   * <code>float velocity_uncertainty = 2;</code>
   * @return The velocityUncertainty.
   */
  @java.lang.Override
  public float getVelocityUncertainty() {
    return velocityUncertainty_;
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
    if (java.lang.Float.floatToRawIntBits(velocity_) != 0) {
      output.writeFloat(1, velocity_);
    }
    if (java.lang.Float.floatToRawIntBits(velocityUncertainty_) != 0) {
      output.writeFloat(2, velocityUncertainty_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (java.lang.Float.floatToRawIntBits(velocity_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(1, velocity_);
    }
    if (java.lang.Float.floatToRawIntBits(velocityUncertainty_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeFloatSize(2, velocityUncertainty_);
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
    if (!(obj instanceof com.thalesgroup.ibis.protos.Velocity)) {
      return super.equals(obj);
    }
    com.thalesgroup.ibis.protos.Velocity other = (com.thalesgroup.ibis.protos.Velocity) obj;

    if (java.lang.Float.floatToIntBits(getVelocity())
        != java.lang.Float.floatToIntBits(
            other.getVelocity())) return false;
    if (java.lang.Float.floatToIntBits(getVelocityUncertainty())
        != java.lang.Float.floatToIntBits(
            other.getVelocityUncertainty())) return false;
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
    hash = (37 * hash) + VELOCITY_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getVelocity());
    hash = (37 * hash) + VELOCITY_UNCERTAINTY_FIELD_NUMBER;
    hash = (53 * hash) + java.lang.Float.floatToIntBits(
        getVelocityUncertainty());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.thalesgroup.ibis.protos.Velocity parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.thalesgroup.ibis.protos.Velocity parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thalesgroup.ibis.protos.Velocity parseFrom(
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
  public static Builder newBuilder(com.thalesgroup.ibis.protos.Velocity prototype) {
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
   * Protobuf type {@code ibis.Velocity}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ibis.Velocity)
      com.thalesgroup.ibis.protos.VelocityOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Velocity_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Velocity_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.thalesgroup.ibis.protos.Velocity.class, com.thalesgroup.ibis.protos.Velocity.Builder.class);
    }

    // Construct using com.thalesgroup.ibis.protos.Velocity.newBuilder()
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
      velocity_ = 0F;
      velocityUncertainty_ = 0F;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.thalesgroup.ibis.protos.AnswerOuterClass.internal_static_ibis_Velocity_descriptor;
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Velocity getDefaultInstanceForType() {
      return com.thalesgroup.ibis.protos.Velocity.getDefaultInstance();
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Velocity build() {
      com.thalesgroup.ibis.protos.Velocity result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Velocity buildPartial() {
      com.thalesgroup.ibis.protos.Velocity result = new com.thalesgroup.ibis.protos.Velocity(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.thalesgroup.ibis.protos.Velocity result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.velocity_ = velocity_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.velocityUncertainty_ = velocityUncertainty_;
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
      if (other instanceof com.thalesgroup.ibis.protos.Velocity) {
        return mergeFrom((com.thalesgroup.ibis.protos.Velocity)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.thalesgroup.ibis.protos.Velocity other) {
      if (other == com.thalesgroup.ibis.protos.Velocity.getDefaultInstance()) return this;
      if (other.getVelocity() != 0F) {
        setVelocity(other.getVelocity());
      }
      if (other.getVelocityUncertainty() != 0F) {
        setVelocityUncertainty(other.getVelocityUncertainty());
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
              velocity_ = input.readFloat();
              bitField0_ |= 0x00000001;
              break;
            } // case 13
            case 21: {
              velocityUncertainty_ = input.readFloat();
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

    private float velocity_ ;
    /**
     * <pre>
     * Vitesse de la piste 
     * </pre>
     *
     * <code>float velocity = 1;</code>
     * @return The velocity.
     */
    @java.lang.Override
    public float getVelocity() {
      return velocity_;
    }
    /**
     * <pre>
     * Vitesse de la piste 
     * </pre>
     *
     * <code>float velocity = 1;</code>
     * @param value The velocity to set.
     * @return This builder for chaining.
     */
    public Builder setVelocity(float value) {

      velocity_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Vitesse de la piste 
     * </pre>
     *
     * <code>float velocity = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearVelocity() {
      bitField0_ = (bitField0_ & ~0x00000001);
      velocity_ = 0F;
      onChanged();
      return this;
    }

    private float velocityUncertainty_ ;
    /**
     * <pre>
     * Incertitude vitesse
     * </pre>
     *
     * <code>float velocity_uncertainty = 2;</code>
     * @return The velocityUncertainty.
     */
    @java.lang.Override
    public float getVelocityUncertainty() {
      return velocityUncertainty_;
    }
    /**
     * <pre>
     * Incertitude vitesse
     * </pre>
     *
     * <code>float velocity_uncertainty = 2;</code>
     * @param value The velocityUncertainty to set.
     * @return This builder for chaining.
     */
    public Builder setVelocityUncertainty(float value) {

      velocityUncertainty_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Incertitude vitesse
     * </pre>
     *
     * <code>float velocity_uncertainty = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearVelocityUncertainty() {
      bitField0_ = (bitField0_ & ~0x00000002);
      velocityUncertainty_ = 0F;
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


    // @@protoc_insertion_point(builder_scope:ibis.Velocity)
  }

  // @@protoc_insertion_point(class_scope:ibis.Velocity)
  private static final com.thalesgroup.ibis.protos.Velocity DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.thalesgroup.ibis.protos.Velocity();
  }

  public static com.thalesgroup.ibis.protos.Velocity getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Velocity>
      PARSER = new com.google.protobuf.AbstractParser<Velocity>() {
    @java.lang.Override
    public Velocity parsePartialFrom(
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

  public static com.google.protobuf.Parser<Velocity> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Velocity> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.thalesgroup.ibis.protos.Velocity getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

