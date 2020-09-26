using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BlockMan : MonoBehaviour {

	private string Text;

	private bool show;

    public GameObject block;

	private void OnTriggerEnter2D(Collider2D collider)
	{
		Character unit = collider.GetComponent<Character> ();
		show = true;
		if (unit && show == true) 
		{
			Text = "Я. ЕСТЬ. БЛОК-МЕТОД!";
		}

        if (Character.quest == true)
        {
            Destroy(block);
            Destroy(gameObject);   
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
		GUI.Label (new Rect (500, 100, 300, 300), "<size=20>" + Text + "</size>", style);
	}
}
