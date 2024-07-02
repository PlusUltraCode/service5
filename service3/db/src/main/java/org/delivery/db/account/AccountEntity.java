package org.delivery.db.account;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;


@Entity
@Table(name ="account")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AccountEntity extends BaseEntity {


}
