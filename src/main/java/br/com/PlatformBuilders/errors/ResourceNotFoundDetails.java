package br.com.PlatformBuilders.errors;

public class ResourceNotFoundDetails {
	private String title;
	private int status;
	private String detail;
	private long timestamo;
	private String developerMessage;

	private ResourceNotFoundDetails() {

	}

	public String getTitle() {
		return title;
	}

	public int getStatus() {
		return status;
	}

	public String getDetail() {
		return detail;
	}

	public long getTimestamo() {
		return timestamo;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private long timestamo;
		private String developerMessage;

		private Builder() {
		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder timestamo(long timestamo) {
			this.timestamo = timestamo;
			return this;
		}

		public Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public ResourceNotFoundDetails build() {
			ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
			resourceNotFoundDetails.title = this.title;
			resourceNotFoundDetails.detail = this.detail;
			resourceNotFoundDetails.developerMessage = this.developerMessage;
			resourceNotFoundDetails.timestamo = this.timestamo;
			resourceNotFoundDetails.status = this.status;
			return resourceNotFoundDetails;
		}
	}
}
