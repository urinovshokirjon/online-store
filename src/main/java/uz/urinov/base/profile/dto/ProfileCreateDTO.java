package uz.urinov.base.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import uz.urinov.base.profile.enums.ProfileAuthRole;


@Getter
@Setter
public class ProfileCreateDTO {

    @Size(min = 3, max = 50, message = "Berilgan name uzunligi 3 va 50 orasida bo'lishi kerak")
    @NotBlank(message = "name bo'sh bo'lishi mumkin emas")
    private String name;

    @Size(min = 3, max = 50, message = "Berilgan surname uzunligi 3 va 50 orasida bo'lishi kerak")
    @NotBlank(message = "Surname bo'sh bo'lishi mumkin emas")
    private String surname;


    @Pattern(regexp = "^\\+998\\d{9}$", message = "Noto'g'ri telefon raqami")
    @NotBlank(message = "Phone bo'sh bo'lishi mumkin emas")
    private String phone;


    @Size(min = 4, max = 50, message = "Berilgan password uzunligi 3 va 50 orasida bo'lishi kerak")
    @NotBlank(message = "Password bo'sh bo'lishi mumkin emas")
    private String password;

    @NotNull
    private ProfileAuthRole role;



}
