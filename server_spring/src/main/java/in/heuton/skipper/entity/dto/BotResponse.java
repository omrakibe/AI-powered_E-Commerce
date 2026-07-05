package in.heuton.skipper.entity.dto;

import lombok.Data;

@Data
public class BotResponse
{
    private String response;

    public BotResponse(String response)
    {
        this.response = response;
    }

}
