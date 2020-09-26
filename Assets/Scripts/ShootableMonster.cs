using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShootableMonster : Monster {

	private float rate = 1.0f;
	private Bullet bullet;
    public Transform player;
    public string ShooterName;

	public bool liveCount = true;

	private float speed = 2.0f;

	private Vector3 direction;

	Character ch;

	public static bool damage;
    public static bool shoot;
    private float playerDistance;

    protected override void Awake()
	{
		bullet = Resources.Load<Bullet> ("Bullet");
	}

	protected override void Start () 
	{
		ch = new Character ();
		direction = transform.right;
        shoot = false;
		damage = false;

        InvokeRepeating("Attack", rate, rate);
    }

	protected override void Update () 
	{
        Vector3 position = transform.position;
        position.x += 10f;
        position.y -= 0.5f;
        playerDistance = Vector3.Distance(position, player.position);
        Move ();
    }

    public void Attack()
    {
        if (playerDistance < 8f)
            Shoot();
    }

    private void Shoot()
	{
		Vector3 position = transform.position;
		position.x += 10f;
		position.y -= 0.5f;
		Bullet newBullet = Instantiate (bullet, position, bullet.transform.rotation) as Bullet;

		newBullet.Parent = gameObject;
		newBullet.Direction = -newBullet.transform.right;
		if (direction == transform.right) 
		{
			newBullet.Direction = newBullet.transform.right;
		} 
		else 
		{
			newBullet.Direction = -newBullet.transform.right;
		}
	}

	private void Move()
	{
		transform.position = Vector3.MoveTowards (transform.position, transform.position + direction, speed * Time.deltaTime);
	}

	protected override void OnTriggerEnter2D (Collider2D collider)
	{
		if (collider.tag == "Trigger") 
		{
			direction *= -1.0F;
		}

		if (collider.tag == "Trigger2") 
		{
			direction *= -1.0F;
		}

        Bullet bullet = collider.gameObject.GetComponent<Bullet> ();

		if (bullet && bullet.Parent !=gameObject) 
		{
			damage = true;
			ReceiveDamage ();
		}
	}
}
