package br.com.odvox.vgames.services.game.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Locale;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import com.amazonaws.util.IOUtils;

@Service
public class VoiceService {
	
	private String locutor;
	private Locale locale = new Locale("pt", "BR");
	private AmazonPollyClient polly;
	private Voice voice;
	private Region region = Region.getRegion(Regions.AP_SOUTHEAST_1);
	
	@PostConstruct
	public void configure() {
		if (null == this.locutor) {
			if (new Random().nextBoolean()) {
				if (this.locale.getLanguage().equalsIgnoreCase("pt")) {
					this.locutor = "Vitória";
				} else if (this.locale.getLanguage().equalsIgnoreCase("en")) {
					this.locutor = "Joanna";
				}
			} else {
				if (this.locale.getLanguage().equalsIgnoreCase("pt")) {
					this.locutor = "Ricardo";
				} else if (this.locale.getLanguage().equalsIgnoreCase("en")) {
					this.locutor = "Joey";
				}
			}
		}
		// create an Amazon Polly client in a specific region
		this.polly = new AmazonPollyClient(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
		if (null != this.polly) {
			this.polly.setRegion(this.region);
			// Create describe voices request.
			DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

			// Synchronously ask Amazon Polly to describe available TTS voices.
			DescribeVoicesResult describeVoicesResult = this.polly.describeVoices(describeVoicesRequest);

			for (Voice voz : describeVoicesResult.getVoices()) {
				if (voz.getName().equalsIgnoreCase(this.locutor)) {
					this.voice = voz;
				}
			}
		}
	}
	
	public String getVozURL(final String texto) throws UnsupportedEncodingException {
		InputStream speechStream = null;
		try {
			speechStream = synthesize(texto, OutputFormat.Mp3, this.voice, this.polly);
			byte[] bytes = IOUtils.toByteArray(speechStream);
			String vozBase64 = Base64.getEncoder().encodeToString(bytes);
			return "data:audio/mp3;base64," + vozBase64;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private InputStream synthesize(String text, OutputFormat format, Voice voice, AmazonPollyClient polly)
			throws IOException {
		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
				.withOutputFormat(format);
		SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);
		return synthRes.getAudioStream();
	}
}
