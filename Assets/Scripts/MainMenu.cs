using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MainMenu : MonoBehaviour {

	void Update()
	{
		if (Input.GetKey (KeyCode.B) && Input.GetKey (KeyCode.H)) 
		{
			Application.LoadLevel ("SecretLevel");
		}
	}

	void OnGUI()
	{
		GUIStyle style = new GUIStyle ();
		style.richText = true;
		if (GUI.Button (new Rect (Screen.width / 2 - 80, Screen.height / 2 + 50, 120, 30), "Начать убивать!")) 
		{
				Application.LoadLevel ("FirstScene");
		}
		if (GUI.Button (new Rect (Screen.width / 2 - 80, Screen.height / 2 + 100, 120, 30), "Выйти к чертям!")) 
		{
			Application.Quit();
		}
		GUI.Label (new Rect (Screen.width / 2 - 200, Screen.height / 2 + 300, 300, 300), "<size=20>" + "2018, KazinGames. Все права защищены!" + "</size>", style);
	}

}
