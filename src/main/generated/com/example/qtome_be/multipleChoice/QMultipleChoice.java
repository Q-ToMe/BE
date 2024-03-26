package com.example.qtome_be.multipleChoice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMultipleChoice is a Querydsl query type for MultipleChoice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMultipleChoice extends EntityPathBase<MultipleChoice> {

    private static final long serialVersionUID = 1951264751L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMultipleChoice multipleChoice = new QMultipleChoice("multipleChoice");

    public final StringPath bodyText = createString("bodyText");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAnswer = createBoolean("isAnswer");

    public final com.example.qtome_be.question.QQuestion question;

    public QMultipleChoice(String variable) {
        this(MultipleChoice.class, forVariable(variable), INITS);
    }

    public QMultipleChoice(Path<? extends MultipleChoice> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMultipleChoice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMultipleChoice(PathMetadata metadata, PathInits inits) {
        this(MultipleChoice.class, metadata, inits);
    }

    public QMultipleChoice(Class<? extends MultipleChoice> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new com.example.qtome_be.question.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}

