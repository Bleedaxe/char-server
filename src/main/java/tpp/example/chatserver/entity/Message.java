package tpp.example.chatserver.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "message")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull private String type;

  @NotNull private String payload;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
