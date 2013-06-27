package jp.gr.java_conf.naoki_kamimura.json;

public class PinpointLocations {
	private String name;// 市区町村
	private String link;// 対応するlivedoor天気情報のURL
	private String title;// 天気予報の見出し

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
