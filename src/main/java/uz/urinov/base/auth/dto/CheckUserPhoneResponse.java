package uz.urinov.base.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.urinov.base.auth.enums.StatusAuth;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckUserPhoneResponse {
    private StatusAuth status;
}
