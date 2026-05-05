package in.heuton.skipper.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails
{
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
