package models.user;

import java.util.List;

public record InvalidPutUpdateUserResponseModel(List<String> firstName, List<String> lastName,
                                                List<String> email) {}
