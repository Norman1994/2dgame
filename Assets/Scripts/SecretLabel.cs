using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SecretLabel : MonoBehaviour {

	private string Text;

	private bool show;

	private void OnTriggerEnter2D(Collider2D collider)
	{
		Character unit = collider.GetComponent<Character> ();
		show = true;
		if (unit && show == true) 
		{
			Text = "RIP, Billy." + "\n" + "We miss..."
				+ "\n" + "14.07.1969 - 03.03.2018";
		}

	}

	private void OnTriggerExit2D(Collider2D collider)
	{
		show = false;
		Character unit = collider.GetComponent<Character> ();
		if (unit && show == false) 
		{
			Text = "";
		}
	}

	void OnGUI() 
	{
		GUIStyle style = new GUIStyle ();
		style.richText = true;
		GUI.Label (new Rect (Screen.width / 2 - 70, Screen.height / 2 - 400, 300, 300), "<size=25>" + "<color=white>" + Text + "</color>" + "</size>", style);
	}
}
