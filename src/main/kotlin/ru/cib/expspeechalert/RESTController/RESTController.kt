package ru.cib.expspeechalert.RESTController

import org.springframework.web.bind.annotation.*
import ru.cib.expspeechalert.SpeechSynthesiser.SpeechHook


@RestController
@RequestMapping("/voicer")
class RESTController(
        val speechHook: SpeechHook
)
{
    @GetMapping("/generateservername/{server}&{path}")
    fun voice_server(@PathVariable server:String,@PathVariable path:String)
    {
        speechHook.generate_server_name(server,path)
    }
    @GetMapping("/generatemessage/{message}&{path}")
    fun voice_message(@PathVariable message:String,@PathVariable path:String)
    {
        speechHook.generate_message(message,path)
    }
    @GetMapping("/generateservernamemil/{server}&{path}")
    fun voice_server_mil(@PathVariable server:String,@PathVariable path:String)
    {
        speechHook.generate_server_name_mil(server,path)
    }


}