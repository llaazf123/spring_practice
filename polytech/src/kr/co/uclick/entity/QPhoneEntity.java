package kr.co.uclick.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneEntity is a Querydsl query type for PhoneEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPhoneEntity extends EntityPathBase<PhoneEntity> {

    private static final long serialVersionUID = -1912584954L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneEntity phoneEntity = new QPhoneEntity("phoneEntity");

    public final StringPath carrier = createString("carrier");

    public final NumberPath<Long> phoneId = createNumber("phoneId", Long.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final QUserEntity userEntity;

    public QPhoneEntity(String variable) {
        this(PhoneEntity.class, forVariable(variable), INITS);
    }

    public QPhoneEntity(Path<? extends PhoneEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneEntity(PathMetadata metadata, PathInits inits) {
        this(PhoneEntity.class, metadata, inits);
    }

    public QPhoneEntity(Class<? extends PhoneEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userEntity = inits.isInitialized("userEntity") ? new QUserEntity(forProperty("userEntity")) : null;
    }

}

