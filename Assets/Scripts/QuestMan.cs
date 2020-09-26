using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuestMan : MonoBehaviour 
{
	private string Text;

	private bool show;

	private void OnTriggerEnter2D(Collider2D collider)
	{
		Character unit = collider.GetComponent<Character> ();
		show = true;
        if (unit && show == true && Character.quest == false)
        {
            Text = "Привет, Дениска! Ты спешишь остановить нашествие С#? Тогда я помогу тебе, ибо Java - язык богов!" + "\n" + "Короче, у меня есть важное сообщение для тебя." + "\n" + "Злобный Граф Куликов создал метод с входным параметром, он где-то на уровне! " + "\n"
             + "Найди его, и метод позволит тебе пройти дальше!";
        }
        else if (unit && show == true && Character.quest == true)
        {
            Text = "Ты истиный самец! Иди, и принеси нам победу. Java для богов!";
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
		GUI.Label (new Rect (10, 100, 300, 300), "<size=20>" + Text + "</size>", style);
	}
		
}
