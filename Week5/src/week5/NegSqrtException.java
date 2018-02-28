package week5;

import java.io.*;

public class NegSqrtException extends Exception {

		private String specialMessage;

		public String getSpecialMessage() {
			return specialMessage;
		}

		public void setSpecialMessage(String specialMessage) {
			this.specialMessage = specialMessage;
		}

		public NegSqrtException(String message) {
			this.specialMessage = message;
		}

		public NegSqrtException() {
		
		}
			
		
		
}