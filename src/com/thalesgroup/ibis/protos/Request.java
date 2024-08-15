// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request.proto

// Protobuf Java Version: 3.25.1
package com.thalesgroup.ibis.protos;

/**
 * <pre>
 * Message de tasking des capteurs
 * </pre>
 *
 * Protobuf type {@code ibis.Request}
 */
public final class Request extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ibis.Request)
    RequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Request.newBuilder() to construct.
  private Request(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Request() {
    sensorModeIds_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
    trackId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Request();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.thalesgroup.ibis.protos.RequestOuterClass.internal_static_ibis_Request_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.thalesgroup.ibis.protos.RequestOuterClass.internal_static_ibis_Request_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.thalesgroup.ibis.protos.Request.class, com.thalesgroup.ibis.protos.Request.Builder.class);
  }

  public static final int SENSOR_MODE_IDS_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private com.google.protobuf.LazyStringArrayList sensorModeIds_ =
      com.google.protobuf.LazyStringArrayList.emptyList();
  /**
   * <pre>
   * Identifiants des modes capteurs
   * </pre>
   *
   * <code>repeated string sensor_mode_ids = 1;</code>
   * @return A list containing the sensorModeIds.
   */
  public com.google.protobuf.ProtocolStringList
      getSensorModeIdsList() {
    return sensorModeIds_;
  }
  /**
   * <pre>
   * Identifiants des modes capteurs
   * </pre>
   *
   * <code>repeated string sensor_mode_ids = 1;</code>
   * @return The count of sensorModeIds.
   */
  public int getSensorModeIdsCount() {
    return sensorModeIds_.size();
  }
  /**
   * <pre>
   * Identifiants des modes capteurs
   * </pre>
   *
   * <code>repeated string sensor_mode_ids = 1;</code>
   * @param index The index of the element to return.
   * @return The sensorModeIds at the given index.
   */
  public java.lang.String getSensorModeIds(int index) {
    return sensorModeIds_.get(index);
  }
  /**
   * <pre>
   * Identifiants des modes capteurs
   * </pre>
   *
   * <code>repeated string sensor_mode_ids = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the sensorModeIds at the given index.
   */
  public com.google.protobuf.ByteString
      getSensorModeIdsBytes(int index) {
    return sensorModeIds_.getByteString(index);
  }

  public static final int TRACK_ID_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object trackId_ = "";
  /**
   * <pre>
   * identifiant de la piste
   * </pre>
   *
   * <code>string track_id = 2;</code>
   * @return The trackId.
   */
  @java.lang.Override
  public java.lang.String getTrackId() {
    java.lang.Object ref = trackId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      trackId_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * identifiant de la piste
   * </pre>
   *
   * <code>string track_id = 2;</code>
   * @return The bytes for trackId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTrackIdBytes() {
    java.lang.Object ref = trackId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      trackId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DISTANCE_FIELD_NUMBER = 3;
  private long distance_ = 0L;
  /**
   * <pre>
   * Distance à la plateforme
   * </pre>
   *
   * <code>int64 distance = 3;</code>
   * @return The distance.
   */
  @java.lang.Override
  public long getDistance() {
    return distance_;
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
    for (int i = 0; i < sensorModeIds_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, sensorModeIds_.getRaw(i));
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(trackId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, trackId_);
    }
    if (distance_ != 0L) {
      output.writeInt64(3, distance_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < sensorModeIds_.size(); i++) {
        dataSize += computeStringSizeNoTag(sensorModeIds_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getSensorModeIdsList().size();
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(trackId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, trackId_);
    }
    if (distance_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, distance_);
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
    if (!(obj instanceof com.thalesgroup.ibis.protos.Request)) {
      return super.equals(obj);
    }
    com.thalesgroup.ibis.protos.Request other = (com.thalesgroup.ibis.protos.Request) obj;

    if (!getSensorModeIdsList()
        .equals(other.getSensorModeIdsList())) return false;
    if (!getTrackId()
        .equals(other.getTrackId())) return false;
    if (getDistance()
        != other.getDistance()) return false;
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
    if (getSensorModeIdsCount() > 0) {
      hash = (37 * hash) + SENSOR_MODE_IDS_FIELD_NUMBER;
      hash = (53 * hash) + getSensorModeIdsList().hashCode();
    }
    hash = (37 * hash) + TRACK_ID_FIELD_NUMBER;
    hash = (53 * hash) + getTrackId().hashCode();
    hash = (37 * hash) + DISTANCE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDistance());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.thalesgroup.ibis.protos.Request parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.thalesgroup.ibis.protos.Request parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.thalesgroup.ibis.protos.Request parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.thalesgroup.ibis.protos.Request parseFrom(
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
  public static Builder newBuilder(com.thalesgroup.ibis.protos.Request prototype) {
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
   * <pre>
   * Message de tasking des capteurs
   * </pre>
   *
   * Protobuf type {@code ibis.Request}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ibis.Request)
      com.thalesgroup.ibis.protos.RequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.thalesgroup.ibis.protos.RequestOuterClass.internal_static_ibis_Request_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.thalesgroup.ibis.protos.RequestOuterClass.internal_static_ibis_Request_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.thalesgroup.ibis.protos.Request.class, com.thalesgroup.ibis.protos.Request.Builder.class);
    }

    // Construct using com.thalesgroup.ibis.protos.Request.newBuilder()
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
      sensorModeIds_ =
          com.google.protobuf.LazyStringArrayList.emptyList();
      trackId_ = "";
      distance_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.thalesgroup.ibis.protos.RequestOuterClass.internal_static_ibis_Request_descriptor;
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Request getDefaultInstanceForType() {
      return com.thalesgroup.ibis.protos.Request.getDefaultInstance();
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Request build() {
      com.thalesgroup.ibis.protos.Request result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.thalesgroup.ibis.protos.Request buildPartial() {
      com.thalesgroup.ibis.protos.Request result = new com.thalesgroup.ibis.protos.Request(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.thalesgroup.ibis.protos.Request result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        sensorModeIds_.makeImmutable();
        result.sensorModeIds_ = sensorModeIds_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.trackId_ = trackId_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.distance_ = distance_;
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
      if (other instanceof com.thalesgroup.ibis.protos.Request) {
        return mergeFrom((com.thalesgroup.ibis.protos.Request)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.thalesgroup.ibis.protos.Request other) {
      if (other == com.thalesgroup.ibis.protos.Request.getDefaultInstance()) return this;
      if (!other.sensorModeIds_.isEmpty()) {
        if (sensorModeIds_.isEmpty()) {
          sensorModeIds_ = other.sensorModeIds_;
          bitField0_ |= 0x00000001;
        } else {
          ensureSensorModeIdsIsMutable();
          sensorModeIds_.addAll(other.sensorModeIds_);
        }
        onChanged();
      }
      if (!other.getTrackId().isEmpty()) {
        trackId_ = other.trackId_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (other.getDistance() != 0L) {
        setDistance(other.getDistance());
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
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();
              ensureSensorModeIdsIsMutable();
              sensorModeIds_.add(s);
              break;
            } // case 10
            case 18: {
              trackId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              distance_ = input.readInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
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

    private com.google.protobuf.LazyStringArrayList sensorModeIds_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
    private void ensureSensorModeIdsIsMutable() {
      if (!sensorModeIds_.isModifiable()) {
        sensorModeIds_ = new com.google.protobuf.LazyStringArrayList(sensorModeIds_);
      }
      bitField0_ |= 0x00000001;
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @return A list containing the sensorModeIds.
     */
    public com.google.protobuf.ProtocolStringList
        getSensorModeIdsList() {
      sensorModeIds_.makeImmutable();
      return sensorModeIds_;
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @return The count of sensorModeIds.
     */
    public int getSensorModeIdsCount() {
      return sensorModeIds_.size();
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @param index The index of the element to return.
     * @return The sensorModeIds at the given index.
     */
    public java.lang.String getSensorModeIds(int index) {
      return sensorModeIds_.get(index);
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @param index The index of the value to return.
     * @return The bytes of the sensorModeIds at the given index.
     */
    public com.google.protobuf.ByteString
        getSensorModeIdsBytes(int index) {
      return sensorModeIds_.getByteString(index);
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @param index The index to set the value at.
     * @param value The sensorModeIds to set.
     * @return This builder for chaining.
     */
    public Builder setSensorModeIds(
        int index, java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      ensureSensorModeIdsIsMutable();
      sensorModeIds_.set(index, value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @param value The sensorModeIds to add.
     * @return This builder for chaining.
     */
    public Builder addSensorModeIds(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      ensureSensorModeIdsIsMutable();
      sensorModeIds_.add(value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @param values The sensorModeIds to add.
     * @return This builder for chaining.
     */
    public Builder addAllSensorModeIds(
        java.lang.Iterable<java.lang.String> values) {
      ensureSensorModeIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, sensorModeIds_);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSensorModeIds() {
      sensorModeIds_ =
        com.google.protobuf.LazyStringArrayList.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Identifiants des modes capteurs
     * </pre>
     *
     * <code>repeated string sensor_mode_ids = 1;</code>
     * @param value The bytes of the sensorModeIds to add.
     * @return This builder for chaining.
     */
    public Builder addSensorModeIdsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      ensureSensorModeIdsIsMutable();
      sensorModeIds_.add(value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object trackId_ = "";
    /**
     * <pre>
     * identifiant de la piste
     * </pre>
     *
     * <code>string track_id = 2;</code>
     * @return The trackId.
     */
    public java.lang.String getTrackId() {
      java.lang.Object ref = trackId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        trackId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * identifiant de la piste
     * </pre>
     *
     * <code>string track_id = 2;</code>
     * @return The bytes for trackId.
     */
    public com.google.protobuf.ByteString
        getTrackIdBytes() {
      java.lang.Object ref = trackId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        trackId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * identifiant de la piste
     * </pre>
     *
     * <code>string track_id = 2;</code>
     * @param value The trackId to set.
     * @return This builder for chaining.
     */
    public Builder setTrackId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      trackId_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * identifiant de la piste
     * </pre>
     *
     * <code>string track_id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTrackId() {
      trackId_ = getDefaultInstance().getTrackId();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * identifiant de la piste
     * </pre>
     *
     * <code>string track_id = 2;</code>
     * @param value The bytes for trackId to set.
     * @return This builder for chaining.
     */
    public Builder setTrackIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      trackId_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private long distance_ ;
    /**
     * <pre>
     * Distance à la plateforme
     * </pre>
     *
     * <code>int64 distance = 3;</code>
     * @return The distance.
     */
    @java.lang.Override
    public long getDistance() {
      return distance_;
    }
    /**
     * <pre>
     * Distance à la plateforme
     * </pre>
     *
     * <code>int64 distance = 3;</code>
     * @param value The distance to set.
     * @return This builder for chaining.
     */
    public Builder setDistance(long value) {

      distance_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Distance à la plateforme
     * </pre>
     *
     * <code>int64 distance = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearDistance() {
      bitField0_ = (bitField0_ & ~0x00000004);
      distance_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:ibis.Request)
  }

  // @@protoc_insertion_point(class_scope:ibis.Request)
  private static final com.thalesgroup.ibis.protos.Request DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.thalesgroup.ibis.protos.Request();
  }

  public static com.thalesgroup.ibis.protos.Request getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Request>
      PARSER = new com.google.protobuf.AbstractParser<Request>() {
    @java.lang.Override
    public Request parsePartialFrom(
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

  public static com.google.protobuf.Parser<Request> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Request> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.thalesgroup.ibis.protos.Request getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

